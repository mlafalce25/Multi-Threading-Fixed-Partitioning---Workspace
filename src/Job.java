
public  class Job implements Runnable{



	@Override
	public String toString() {
		return "Job [name=" + id + ", size=" + size + "]";
	}
	protected int id;
	protected int location;
	protected int size;
	protected int status;//0 is waiting, 1 is running, 2 is completed
	protected long endTime;
	protected int executionTime;
	
	/**
	 * @param id
	 * @param size
	 * @param executionTime
	 */
	public Job(int id, int size, int executionTime) {
		super();
		this.id = id;
		this.size = size;
		this.executionTime = executionTime;
		this.status = 0;
	}
	/**
	 * @return the id
	 */
	public int getIdnum() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setIdnum(int id) {
		this.id = id;
	}
	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(int location) {
		this.location = location;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime() {
		this.endTime = System.currentTimeMillis() + executionTime;
	}
	/**
	 * @return the executionTime
	 */
	public long getExecutionTime() {
		return executionTime;
	}
	/**
	 * @param executionTime the executionTime to set
	 */
	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}



	@Override
	public void run() {
		Thread thread = new Thread();

		do {
			setEndTime();
			thread.start();
			try {
			thread.sleep(executionTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(System.currentTimeMillis() < endTime);
		Thread.yield();
		Main.deleteJob();		
	}



}


