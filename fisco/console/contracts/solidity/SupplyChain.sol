pragma solidity >=0.4.22;
/**
 * The supplyChain contract does this and that...
 */
contract SupplyChain {
  constructor(address a) public {
    admin = a;
    organizations[admin] = Organization(Tag.Bank, 0, 0, false, address(0));
  }

  struct Bill {
    address owner;
    address borrower;
    address verifier;
    uint value;
    uint replayData;
  }
  
  enum Tag { Bank, Enterprise, Supplier }
  
  /*
  * bank: tag / asVerifier / finaning
  * enterprise: tag / asOwer, asBorrower / balance / isGuaranteed, guaranteeBank
  * suppier: tag / asOwer, asBorrower / balance / finaning
  */
  struct Organization {
    Tag tag;
   //  Bill[] asVerifier;
   //  Bill[] asOwer;
    // Bill[] asBorrower;
    uint balance;
    uint leftValue;
    bool isGuaranteed;
    address guaranteeBank;
    mapping (address => uint) finaning;
  }

  address admin;
  address[] registerList;
  Bill[] billList;
  mapping (address => Organization) organizations;

  function Register (address a, uint tag, uint value) public returns(bool, string memory) {
    require (msg.sender == admin);
    for (uint i = 0; i < registerList.length; ++i) {
      if (a == registerList[i]) {
        return (false, "Has been registered");
      }
    }
    // Bill[] memory aBill = new Bill[](0);
    // Organization memory org = Organization(Tag(tag), new Bill[](0), new Bill[](0), new Bill[](0), 0, false, address(0));
    // Organization memory org = Organization(Tag(tag), aBill, aBill, aBill, 0, false, address(0));
    organizations[a] = Organization(Tag(tag), 0, value, false, address(0));
    registerList.push(a);
    return (true, "Registered Successfully");
  }
  modifier onlyBank () { 
    for (uint i = 0; i < registerList.length; ++i) {
      if (msg.sender == registerList[i] && organizations[msg.sender].tag == Tag.Bank) {
        _; 
      }
    }
  }

  function Transaction (address a, address b, uint value, uint time_limit) public onlyBank returns(bool, string memory) {
    // a, b is not bank && msg.sender has been registered and is bank
    if (organizations[a].tag == Tag.Bank || organizations[b].tag == Tag.Bank) {
      return (false, "Ower and Borrower can not be Bank");
    }
    // enterprise need guararntee
    if (organizations[a].tag == Tag.Enterprise && organizations[a].isGuaranteed == false) {
      organizations[a].isGuaranteed = true;
      organizations[a].guaranteeBank = msg.sender;
      organizations[a].balance += 1000;
    }
    //
    if (organizations[a].balance < value) {
      return (false, "balance < value, not enough credits to owe");
    }
    
    Bill memory bill = Bill(a, b, msg.sender, value, now+time_limit);
    // bill
    billList.push(bill);
    // balance
    organizations[a].balance -= value;
    organizations[b].balance += value;
    return (true, "Transaction Successfully");
  }

  // Suppose there can be only one bill at a time between a and b
  // msg.sender as b, a->b and b->c to a->c
  function Transfer (address a, address c, uint value) public returns(bool, string memory) {
    if (organizations[a].tag == Tag.Bank || organizations[c].tag == Tag.Bank || organizations[msg.sender].tag == Tag.Bank) {
      return (false, "Both parties can not be Bank");
    }
    uint xIndex; // in ab
    uint xValue;
    uint yIndex; // in bc
    uint yValue;
    for (uint i = 0; i < billList.length; ++i) {
      if (billList[i].owner == a && billList[i].borrower == msg.sender) {
        xIndex = i;
        xValue = billList[xIndex].value;
      }
      if (billList[i].owner == msg.sender && billList[i].borrower == c) {
        yIndex = i;
        yValue = billList[yIndex].value;
      }
    }

    // xValue >= yValue >= value
    if (value > yValue) {
      return (false, "value > yValue(b owe c)");
    }
    if (value > xValue) {
      return (false, "value > xValue(a owe b)");
    }
    // change 2 bill, add 1 biil ,every bill according to three organ
    // if value in bill = o, kill it
    // biil in ab
    billList[xIndex].value -= value;
    // bill in bc
    billList[yIndex].value -= value;
    // add bill in ac
    address bank = billList[xIndex].verifier;
    uint replayData = billList[yIndex].replayData;
    billList.push(Bill(a, c, bank, value, replayData));

    if (billList[xIndex].value == 0) {
      delete billList[xIndex];
    }
    if (billList[yIndex].value == 0) {
      delete billList[yIndex];
    }

    return (true, "Transfer Successfully");
  }
  
  function Finaning (address a, uint value) public onlyBank returns(bool, string memory) {
    if (organizations[a].tag != Tag.Supplier) {
      return (false, "Only Supplier can make finaning");
    }
    if (organizations[a].balance < value) {
      return (false, "balance <= value, not enough credits");
    }
    organizations[a].balance -= value;
    organizations[msg.sender].balance += value;
    organizations[a].finaning[msg.sender] += value;
    organizations[msg.sender].finaning[a] += value;
    return (true, "Finaning Successfully");
  }

  function Liquidation (address a, address b) public onlyBank returns(bool, string memory) {
    if (organizations[a].tag == Tag.Bank || organizations[b].tag == Tag.Bank) {
      return (false, "Settlement of both sides can not be bank");
    }
    for (uint i = 0; i < billList.length; ++i) {
      if (billList[i].borrower == a && billList[i].owner == b && billList[i].verifier == msg.sender && billList[i].replayData <= now ) {
        if (organizations[b].leftValue < billList[i].value) {
          return (false, "The ower has not enough money to pay!");
        }
        organizations[b].leftValue -= billList[i].value;
        organizations[a].leftValue += billList[i].value;
        organizations[b].balance += billList[i].value;
        organizations[a].balance -= billList[i].value;
        delete billList[i];
        return (true, "Liquidation Successfully");
      }
    }
  }

  function charge (address a, uint value) public returns(bool, string memory) {
    if (msg.sender != admin) {
      return (false, "Only admin can charge for organizations");
    }
    if (organizations[a].tag == Tag.Bank) {
      return (false, "Bank can not charge");
    }
    organizations[a].leftValue += value;
    return (true, "Charge Successfully");
  }
}



