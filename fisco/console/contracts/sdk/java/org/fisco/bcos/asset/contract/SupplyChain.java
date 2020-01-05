package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class SupplyChain extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b5060405160208061294483398101806040528101908080519060200190929190505050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a0604051908101604052806000600281111561008c57fe5b81526020016000815260200160008152602001600015158152602001600073ffffffffffffffffffffffffffffffffffffffff16815250600360008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff0219169083600281111561014757fe5b0217905550602082015181600101556040820151816002015560608201518160030160006101000a81548160ff02191690831515021790555060808201518160030160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055509050505061276a806101da6000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631d759fb21461007d5780633e55341814610158578063a3ffa9cd14610229578063ce054da4146102fa578063ddf252ad146103e1578063f4b6b127146104d2575b600080fd5b34801561008957600080fd5b506100d2600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001909291905050506105cd565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561011c578082015181840152602081019050610101565b50505050905090810190601f1680156101495780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561016457600080fd5b506101a3600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506108e4565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b838110156101ed5780820151818401526020810190506101d2565b50505050905090810190601f16801561021a5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561023557600080fd5b50610274600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610d45565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b838110156102be5780820151818401526020810190506102a3565b50505050905090810190601f1680156102eb5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561030657600080fd5b5061035b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610f4a565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b838110156103a557808201518184015260208101905061038a565b50505050905090810190601f1680156103d25780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3480156103ed57600080fd5b5061044c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506116c0565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561049657808201518184015260208101905061047b565b50505050905090810190601f1680156104c35780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3480156104de57600080fd5b50610547600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611f5f565b604051808315151515815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610591578082015181840152602081019050610576565b50505050905090810190601f1680156105be5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b6000606060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561062e57600080fd5b600090505b6001805490508110156106f95760018181548110151561064f57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff1614156106ee5760006040805190810160405280601381526020017f486173206265656e207265676973746572656400000000000000000000000000815250925092506108db565b806001019050610633565b60a06040519081016040528086600281111561071157fe5b600281111561071c57fe5b815260200160008152602001858152602001600015158152602001600073ffffffffffffffffffffffffffffffffffffffff16815250600360008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548160ff021916908360028111156107b557fe5b0217905550602082015181600101556040820151816002015560608201518160030160006101000a81548160ff02191690831515021790555060808201518160030160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505060018690806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505060016040805190810160405280601781526020017f52656769737465726564205375636365737366756c6c79000000000000000000815250925092505b50935093915050565b6000606060008090505b600180549050811015610d3d5760018181548110151561090a57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480156109d457506000600281111561097757fe5b600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1660028111156109d257fe5b145b15610d32576002808111156109e557fe5b600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff166002811115610a4057fe5b141515610a885760006040805190810160405280601f81526020017f4f6e6c7920537570706c6965722063616e206d616b652066696e616e696e670081525092509250610d31565b83600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101541015610b3a576000606060405190810160405280602481526020017f62616c616e6365203c3d2076616c75652c206e6f7420656e6f7567682063726581526020017f646974730000000000000000000000000000000000000000000000000000000081525092509250610d31565b83600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254039250508190555083600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254019250508190555083600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555083600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060040160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254019250508190555060016040805190810160405280601581526020017f46696e616e696e67205375636365737366756c6c790000000000000000000000815250925092505b5b8060010190506108ee565b509250929050565b600060606000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e07576000606060405190810160405280602781526020017f4f6e6c792061646d696e2063616e2063686172676520666f72206f7267616e6981526020017f7a6174696f6e730000000000000000000000000000000000000000000000000081525091509150610f43565b60006002811115610e1457fe5b600360008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff166002811115610e6f57fe5b1415610eb65760006040805190810160405280601381526020017f42616e6b2063616e206e6f74206368617267650000000000000000000000000081525091509150610f43565b82600360008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002016000828254019250508190555060016040805190810160405280601381526020017f436861726765205375636365737366756c6c7900000000000000000000000000815250915091505b9250929050565b60006060600080600090505b6001805490508110156116b757600181815481101515610f7257fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614801561103c575060006002811115610fdf57fe5b600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561103a57fe5b145b156116ac576000600281111561104e57fe5b600360008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1660028111156110a957fe5b148061111a5750600060028111156110bd57fe5b600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561111857fe5b145b15611187576000606060405190810160405280602881526020017f536574746c656d656e74206f6620626f74682073696465732063616e206e6f7481526020017f2062652062616e6b000000000000000000000000000000000000000000000000815250935093506116ab565b600091505b6002805490508210156116aa578573ffffffffffffffffffffffffffffffffffffffff166002838154811015156111bf57fe5b906000526020600020906005020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614801561127f57508473ffffffffffffffffffffffffffffffffffffffff1660028381548110151561123557fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b80156112f657503373ffffffffffffffffffffffffffffffffffffffff166002838154811015156112ac57fe5b906000526020600020906005020160020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b801561132257504260028381548110151561130d57fe5b90600052602060002090600502016004015411155b1561169f5760028281548110151561133657fe5b906000526020600020906005020160030154600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002015410156113f9576000606060405190810160405280602581526020017f546865206f77657220686173206e6f7420656e6f756768206d6f6e657920746f81526020017f2070617921000000000000000000000000000000000000000000000000000000815250935093506116ab565b60028281548110151561140857fe5b906000526020600020906005020160030154600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002016000828254039250508190555060028281548110151561147857fe5b906000526020600020906005020160030154600360008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600201600082825401925050819055506002828154811015156114e857fe5b906000526020600020906005020160030154600360008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254019250508190555060028281548110151561155857fe5b906000526020600020906005020160030154600360008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600082825403925050819055506002828154811015156115c857fe5b9060005260206000209060050201600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560038201600090556004820160009055505060016040805190810160405280601881526020017f4c69717569646174696f6e205375636365737366756c6c790000000000000000815250935093506116ab565b81600101915061118c565b5b5b806001019050610f56565b50509250929050565b6000606060008060008060008060008060028111156116db57fe5b600360008e73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561173657fe5b14806117a757506000600281111561174a57fe5b600360008d73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1660028111156117a557fe5b145b806118175750600060028111156117ba57fe5b600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561181557fe5b145b1561185d5760006040805190810160405280601c81526020017f426f746820706172746965732063616e206e6f742062652042616e6b0000000081525098509850611f50565b600092505b600280549050831015611a9e578b73ffffffffffffffffffffffffffffffffffffffff1660028481548110151561189557fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614801561195557503373ffffffffffffffffffffffffffffffffffffffff1660028481548110151561190b57fe5b906000526020600020906005020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b156119815782965060028781548110151561196c57fe5b90600052602060002090600502016003015495505b3373ffffffffffffffffffffffffffffffffffffffff166002848154811015156119a757fe5b906000526020600020906005020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16148015611a6757508a73ffffffffffffffffffffffffffffffffffffffff16600284815481101515611a1d57fe5b906000526020600020906005020160010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b15611a9357829450600285815481101515611a7e57fe5b90600052602060002090600502016003015493505b826001019250611862565b838a1115611ae75760006040805190810160405280601781526020017f76616c7565203e207956616c75652862206f776520632900000000000000000081525098509850611f50565b858a1115611b305760006040805190810160405280601781526020017f76616c7565203e207856616c75652861206f776520622900000000000000000081525098509850611f50565b89600288815481101515611b4057fe5b90600052602060002090600502016003016000828254039250508190555089600286815481101515611b6e57fe5b906000526020600020906005020160030160008282540392505081905550600287815481101515611b9b57fe5b906000526020600020906005020160020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169150600285815481101515611bde57fe5b9060005260206000209060050201600401549050600260a0604051908101604052808e73ffffffffffffffffffffffffffffffffffffffff1681526020018d73ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1681526020018c8152602001838152509080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060608201518160030155608082015181600401555050506000600288815481101515611d8657fe5b9060005260206000209060050201600301541415611e4457600287815481101515611dad57fe5b9060005260206000209060050201600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556003820160009055600482016000905550505b6000600286815481101515611e5557fe5b9060005260206000209060050201600301541415611f1357600285815481101515611e7c57fe5b9060005260206000209060050201600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556002820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556003820160009055600482016000905550505b60016040805190810160405280601581526020017f5472616e73666572205375636365737366756c6c790000000000000000000000815250985098505b50505050505050935093915050565b60006060611f6b6126cc565b60008090505b6001805490508110156126c157600181815481101515611f8d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16148015612057575060006002811115611ffa57fe5b600360003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561205557fe5b145b156126b6576000600281111561206957fe5b600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff1660028111156120c457fe5b14806121355750600060028111156120d857fe5b600360008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561213357fe5b145b156121a2576000606060405190810160405280602181526020017f4f77657220616e6420426f72726f7765722063616e206e6f742062652042616e81526020017f6b00000000000000000000000000000000000000000000000000000000000000815250935093506126b5565b600160028111156121af57fe5b600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000160009054906101000a900460ff16600281111561220a57fe5b14801561226a575060001515600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160009054906101000a900460ff161515145b1561239e576001600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160006101000a81548160ff02191690831515021790555033600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060030160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506103e8600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101600082825401925050819055505b85600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101541015612450576000606060405190810160405280602a81526020017f62616c616e6365203c2076616c75652c206e6f7420656e6f756768206372656481526020017f69747320746f206f776500000000000000000000000000000000000000000000815250935093506126b5565b60a0604051908101604052808973ffffffffffffffffffffffffffffffffffffffff1681526020018873ffffffffffffffffffffffffffffffffffffffff1681526020013373ffffffffffffffffffffffffffffffffffffffff16815260200187815260200186420181525091506002829080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600301556080820151816004015550505085600360008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254039250508190555085600360008973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001016000828254019250508190555060016040805190810160405280601881526020017f5472616e73616374696f6e205375636365737366756c6c790000000000000000815250935093505b5b806001019050611f71565b505094509492505050565b60a060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152602001600081526020016000815250905600a165627a7a723058201e2b6591009ef7e1f2562a4c349fa941bd4fe30a43ed8df49bcf83e55dcb40c40029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"tag\",\"type\":\"uint256\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Register\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Finaning\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"charge\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"b\",\"type\":\"address\"}],\"name\":\"Liquidation\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"c\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"},{\"name\":\"b\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"},{\"name\":\"time_limit\",\"type\":\"uint256\"}],\"name\":\"Transaction\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"a\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_REGISTER = "Register";

    public static final String FUNC_FINANING = "Finaning";

    public static final String FUNC_CHARGE = "charge";

    public static final String FUNC_LIQUIDATION = "Liquidation";

    public static final String FUNC_TRANSFER = "Transfer";

    public static final String FUNC_TRANSACTION = "Transaction";

    @Deprecated
    protected SupplyChain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SupplyChain(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SupplyChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SupplyChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> Register(String a, BigInteger tag, BigInteger value) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tag), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Register(String a, BigInteger tag, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tag), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String RegisterSeq(String a, BigInteger tag, BigInteger value) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tag), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, BigInteger, BigInteger> getRegisterInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple2<Boolean, String> getRegisterOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, String>(

                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> Finaning(String a, BigInteger value) {
        final Function function = new Function(
                FUNC_FINANING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Finaning(String a, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_FINANING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String FinaningSeq(String a, BigInteger value) {
        final Function function = new Function(
                FUNC_FINANING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getFinaningInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_FINANING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple2<Boolean, String> getFinaningOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_FINANING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, String>(

                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> charge(String a, BigInteger value) {
        final Function function = new Function(
                FUNC_CHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void charge(String a, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String chargeSeq(String a, BigInteger value) {
        final Function function = new Function(
                FUNC_CHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getChargeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHARGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple2<Boolean, String> getChargeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CHARGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, String>(

                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> Liquidation(String a, String b) {
        final Function function = new Function(
                FUNC_LIQUIDATION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Liquidation(String a, String b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_LIQUIDATION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(b)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String LiquidationSeq(String a, String b) {
        final Function function = new Function(
                FUNC_LIQUIDATION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(b)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, String> getLiquidationInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LIQUIDATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple2<Boolean, String> getLiquidationOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_LIQUIDATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, String>(

                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> Transfer(String a, String c, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(c), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Transfer(String a, String c, BigInteger value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(c), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String TransferSeq(String a, String c, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(c), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple2<Boolean, String> getTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, String>(

                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> Transaction(String a, String b, BigInteger value, BigInteger time_limit) {
        final Function function = new Function(
                FUNC_TRANSACTION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(b), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(time_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void Transaction(String a, String b, BigInteger value, BigInteger time_limit, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSACTION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(b), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(time_limit)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String TransactionSeq(String a, String b, BigInteger value, BigInteger time_limit) {
        final Function function = new Function(
                FUNC_TRANSACTION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(b), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(time_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, BigInteger> getTransactionInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSACTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public Tuple2<Boolean, String> getTransactionOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSACTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<Boolean, String>(

                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    @Deprecated
    public static SupplyChain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SupplyChain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SupplyChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SupplyChain(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SupplyChain load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SupplyChain(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SupplyChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SupplyChain(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SupplyChain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a)));
        return deployRemoteCall(SupplyChain.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<SupplyChain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a)));
        return deployRemoteCall(SupplyChain.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SupplyChain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a)));
        return deployRemoteCall(SupplyChain.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<SupplyChain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(a)));
        return deployRemoteCall(SupplyChain.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
