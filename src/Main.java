import java.io.File;
import java.io.IOException;
import java.util.*;
/*
 * check system time
 */
public class Main {

	static int[] Free = {25,25,50,50,100,100,100};
	static int[] Busy = {0,0,0,0,0,0,0};
	static ArrayList<Job> jobs = new ArrayList<>();

	public static void main(String[] args) {
		readFile();
		placeJobs();
	}

	//reads files and creates Jobs
	public static void readFile() {
		String[] data = new String[3];
		int x = 0,y = 0, z = 0;
		boolean errorThrown = false;
		File  fileInputs =new File("/Users/Matt/Documents/workspace/Multi-Threading Fixed Partition/src/jobInputs");
		try {
			Scanner in = new Scanner(fileInputs);

			while(in.hasNext()){
				try {
					data = in.next().split(",");
					try {
						x = Integer.parseInt(data[0]);
					}catch(Exception e) {
						errorThrown = true;
					}
					try {
						y = Integer.parseInt(data[1]);
						if(y > 100) {
							errorThrown = true;
						}
					}catch(Exception e) {
						errorThrown = true;

					}
					try {
						z = Integer.parseInt(data[2]);
					}catch(Exception e) {
						errorThrown = true;

					}
					if(errorThrown == false) {
						jobs.add(new Job(x,y,z));
						System.out.println("Job " + x + " created");
					}
					errorThrown = false;

				} 
				catch(NoSuchElementException e) {
					System.out.println("No more Lines");
				}	


			}
		}catch(IOException e) {
			System.out.println("File not Found!!!");
			System.exit(1);
		}
		System.out.println();

	}


	//Allocation
	public static void placeJobs() {
		while(jobs.size() > 0){
			for(int a = 0;a<jobs.size();a++) {
				if(jobs.get(a).getStatus() == 0) {
					for(int x=0;x<Free.length;x++) {
						if(jobs.get(a).getSize() <= Free[x]){
							System.out.println("Job "+jobs.get(a).getIdnum() + " Accepted!");
							Busy[x] = Free[x];
							Free[x] = 0;
							jobs.get(a).setLocation(x);
							jobs.get(a).setStatus(1);
							System.out.println("Job "+jobs.get(a).getIdnum()+" placed: "+x);
							jobs.get(a).run();
							break;
						}
					}	

				}
			}
		}
		System.exit(1);
	}

	//Deallocation
	public static void deleteJob() {
		for(int a = 0;a<jobs.size();a++) {
			if(jobs.get(a).getEndTime() <= System.currentTimeMillis()) {
				Free[jobs.get(a).getLocation()] = Busy[jobs.get(a).getLocation()];
				Busy[jobs.get(a).getLocation()] = 0;
				System.out.println("Job "+jobs.get(a).getIdnum()+" has been finished.");
				System.out.println();
				jobs.remove(a);
			}
		}
	}



}



