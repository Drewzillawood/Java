package mini1;

public class VendingMachineTest {

	public static void main(String[] args) {
		
		VendingMachine vm = new VendingMachine(2,1,0);
		//System.out.println(vm.getQuarters());
		//System.out.println(vm.getDimes());
		//System.out.println(vm.getNickels());
		
		//System.out.println(vm.getTotalValue());
		
		vm.insertDimes(0);
		vm.insertNickels(20);
		vm.insertQuarters(0);
		
		
		System.out.println(vm.getQuarters());
		System.out.println(vm.getDimes());
		System.out.println(vm.getNickels());
		
		//vm.cancel();
		
		//System.out.println(vm.getQuarters());
		//System.out.println(vm.getDimes());
		//System.out.println(vm.getNickels());
		
		//System.out.println(vm.getTotalValue());
		//System.out.println(vm.getBalance());
		
		//System.out.println(vm.getTotalValue());
		vm.purchase(5);
		System.out.println(vm.getQuarters());
		System.out.println(vm.getDimes());
		System.out.println(vm.getNickels());
//		System.out.println(vm.getTotalValue());
		
//		System.out.println(vm.toString());

	}

}
