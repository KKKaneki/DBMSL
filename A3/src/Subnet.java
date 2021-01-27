
import java.io.*;
import java.lang.*;

public class Subnet {

    public static void main(String[] args) throws IOException{
    //     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //     System.out.print("Enter IP : ");
    //     String ip = in.readLine();

    //     String[] ipPeriod = ip.split("\\.");
    //     System.out.println(ip + " " + ipPeriod.length);
    //     if(ipPeriod.length == 4) {
    //         String ipClass = calculateClass(ipPeriod[0]);
    //         System.out.println("IP address belongs to Class : " + ipClass);
    //         String subnetMask = calculateSubnetMask(ipClass);
    //         System.out.println("Default Subnet Mask : " + subnetMask);

    //         System.out.println("Enter the number of addresses in each subnet : ");
    //         int n = Integer.parseInt(in.readLine());

    //         int host_bits = (int)Math.ceil(Math.log(n)/Math.log(2));
    //         int sub = 0;
    //         for(int i=7;i>=(n-host_bits);i--) {
    //             sub += Math.pow(2, i);
    //         }
    //         String subMask;
    //         if(ipClass == "A") {
    //             subMask = "255." + sub + ".0.0";
    //         } else if (ipClass == "B"){
    //             subMask = "255.255." + sub + ".0";
    //         } else {
    //             subMask = "255.255.255." + sub;
    //         }

    //         System.out.println("Subnet Mask :" + subMask);


    //     } else {
    //         System.out.println("Enter a valid IP");
    //     }
    // }

    // public static String calculateClass(String fPeriod){
    //     int f = Integer.parseInt(fPeriod);
    //     if(f <= 127) return "A";
    //     if(f >= 128 && f <= 191) return "B";
    //     if(f >= 192 && f <= 223) return "C";
    //     return "D";
    // }
    
    // public static String calculateSubnetMask(String ipClass){
    //     if(ipClass == "A") return "255.0.0.0";
    //     if(ipClass == "B") return "255.255.0.0";
    //     if(ipClass == "C") return "255.255.255.0";
    //     return "";
    // }
    String s="127.168.2.10";
		int defaultMask[] =new int[32];
		for(int i=0;i<32;i++) {
			defaultMask[i]=1;
		}
		String one="1";
		String zero="0";
		double n;
		System.out.println("Enter the IP address : ");
		Scanner sc =new Scanner(System.in);
		s=sc.next();
		String [] arrOfNo=s.split("\\.");
		System.out.println(s);
		/*for(String i :arrOfNo) {
			System.out.println(i);
		}
		*/
	
		int a,c,b,d;
		a=Integer.parseInt(arrOfNo[0]);
		b=Integer.parseInt(arrOfNo[1]);
		c=Integer.parseInt(arrOfNo[2]);
		d=Integer.parseInt(arrOfNo[3]);
		int az=8-Integer.toBinaryString(a).length();
		int bz=8-Integer.toBinaryString(b).length();
		int cz=8-Integer.toBinaryString(c).length();
		int dz=8-Integer.toBinaryString(d).length();
		String binaryform[]=new String [4];
		binaryform[0]=zero.repeat(az)+Integer.toBinaryString(a);
		binaryform[1]=zero.repeat(bz)+Integer.toBinaryString(b);
		binaryform[2]=zero.repeat(cz)+Integer.toBinaryString(c);
		binaryform[3]=zero.repeat(dz)+Integer.toBinaryString(d);
		String maininput=binaryform[0]+binaryform[1]+binaryform[2]+binaryform[3];
		System.out.println(maininput);
		if(a<=127) {
			System.out.println("Binary Format : "+binaryform[0]+"."+binaryform[1]+"."+binaryform[2]+"."+binaryform[3]);
			System.out.println("class A");
			System.out.println("Default Mask : 255.0.0.0");
			System.out.println("Binary Mask : "+Integer.toBinaryString(255)+"."+zero.repeat(8)+"."+zero.repeat(8)+"."+zero.repeat(8));
			System.out.print("Enter no of addresses in each subnet : ");
			n=sc.nextInt();
			int hostBits=(int)Math.ceil(Math.log(n)/Math.log(2));
			int additionalBits=24-hostBits;
			System.out.println(hostBits+" "+additionalBits);
			
			for(int i=0;i<hostBits;i++) {
				defaultMask[i]=0;
			}
			System.out.print("Subnet Mask : ");
			printIpBinary(defaultMask);
			System.out.print("Subnet Mask(in decimal) : ");
			printIpDecimal(defaultMask);
			System.out.println("No of subnets : "+(int)Math.pow(2,additionalBits));
			System.out.println("No of hosts in each subnet : "+((int)Math.pow(2,hostBits)-2));
			
			System.out.println("Network address : ");
			printNetworkAdd(maininput, hostBits);
			System.out.println("Broadcast address : ");
			printBroadcastAdd(maininput, hostBits);
		}
		else if(a<=191) {
			System.out.println("Binary Format : "+binaryform[0]+"."+binaryform[1]+"."+binaryform[2]+"."+binaryform[3]);
			System.out.println("class B");
			System.out.println("Default Mask : 255.255.0.0");
			System.out.println("Binary Mask : "+Integer.toBinaryString(255)+"."+Integer.toBinaryString(255)+"."+zero.repeat(8)+"."+zero.repeat(8));
			System.out.print("Enter no of addresses in each subnet : ");
			n=sc.nextInt();
			int hostBits=(int)Math.ceil(Math.log(n)/Math.log(2));
			int additionalBits=16-hostBits;
			System.out.println(hostBits+" "+additionalBits);
			
			for(int i=0;i<hostBits;i++) {
				defaultMask[i]=0;
			}
			System.out.print("Subnet Mask : ");
			printIpBinary(defaultMask);
			System.out.print("Subnet Mask(in decimal) : ");
			printIpDecimal(defaultMask);
			System.out.println("No of subnets : "+(int)Math.pow(2,additionalBits));
			System.out.println("No of hosts in each subnet : "+((int)Math.pow(2,hostBits)-2));
			
			System.out.println("Network address : ");
			printNetworkAdd(maininput, hostBits);
			System.out.println("Broadcast address : ");
			printBroadcastAdd(maininput, hostBits);
			
		}
		else if(a<=223) {
			System.out.println("Binary Format : "+binaryform[0]+"."+binaryform[1]+"."+binaryform[2]+"."+binaryform[3]);
			System.out.println("class C");
			System.out.println("Default Mask : 255.255.255.0");
			System.out.println("Binary Mask : "+Integer.toBinaryString(255)+"."+Integer.toBinaryString(255)+"."+Integer.toBinaryString(255)+"."+zero.repeat(8));
			System.out.print("Enter no of addresses in each subnet : ");
			n=sc.nextInt();
			int hostBits=(int)Math.ceil(Math.log(n)/Math.log(2));
			int additionalBits=8-hostBits;
			System.out.println(hostBits+" "+additionalBits);
			
			for(int i=0;i<hostBits;i++) {
				defaultMask[i]=0;
			}
			System.out.print("Subnet Mask : ");
			printIpBinary(defaultMask);
			System.out.print("Subnet Mask(in decimal) : ");
			printIpDecimal(defaultMask);
			System.out.println("No of subnets : "+(int)Math.pow(2,additionalBits));
			System.out.println("No of hosts in each subnet : "+((int)Math.pow(2,hostBits)-2));
			
			System.out.println("Network address : ");
			printNetworkAdd(maininput, hostBits);
			System.out.println("Broadcast address : ");
			printBroadcastAdd(maininput, hostBits);
		}
		sc.close();
	}
	
	public static void printIpBinary(int arr[]) {
		
		for(int i=4;i>0;i--) {
			for(int j=i*8-1;j>=(i-1)*8;j--) {
				//System.out.println(j);
				System.out.print(arr[j]);
			}
			if(i==1) {
				break;
			}
			System.out.print('.');
		}
		System.out.println();
	}
	
	public static void printIpDecimal(int arr[]) {
		for(int i=4;i>0;i--) {
			int s=0;
			for(int j=i*8-1;j>=(i-1)*8;j--) {
				if(arr[j]==1) {
					s+=Math.pow(2, j%8);
				}
			}
			System.out.print(s);
			if(i==1) {
				break;
			}
			System.out.print('.');
		}
		System.out.println();
	}
	
	public static void printNetworkAdd(String arr,int n) {
		int arr2[]=new int[32];
		for(int i=4;i>0;i--) {
			for(int j=i*8-1;j>=0;j--) {
				if(31-j>=n) {
					if(arr.charAt(j)=='1') {
						arr2[31-j]=1;
					}
					else {
						arr2[31-j]=0;
					}
				}
				else {
					arr2[31-j]=0;
				}
			}
			
		}
		printIpBinary(arr2);
		printIpDecimal(arr2);
		System.out.println();
	}
	
	public static void printBroadcastAdd(String arr,int n) {
		int arr2[]=new int[32];
		for(int i=4;i>0;i--) {
			for(int j=i*8-1;j>=0;j--) {
				if(31-j>=n) {
					if(arr.charAt(j)=='1') {
						arr2[31-j]=1;
					}
					else {
						arr2[31-j]=0;
					}
				}
				else {
					arr2[31-j]=1;
				}
			}
			
		}
		printIpBinary(arr2);
		printIpDecimal(arr2);
		System.out.println();
	}



}

