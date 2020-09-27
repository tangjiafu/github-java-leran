package com.debug.kill.server;

class p {
	static{
		System.out.println("p");
	}

	public p(){
		System.out.println("pp");
	}
}

class q extends p {
	static{
		System.out.println("q");
	}

	public q(){
		System.out.println("qq");
	}
}


public class Test {
	public static void main(String[] args){
		q q = new q();
	}

}
