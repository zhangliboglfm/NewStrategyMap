package com.miapsoft.model;

public class TbDimJob implements java.io.Serializable{

	private static final long serialVersionUID = 5505178777943170554L;
	
	// Fields

	private String jobId;
	private String jobDesc;
	private String jobName;

	// Constructors

	/** default constructor */
	public TbDimJob() {
	}

	/** minimal constructor */
	public TbDimJob(String jobId) {
		this.jobId = jobId;
	}

	/** full constructor */
	public TbDimJob(String jobId, String jobDesc, String jobName) {
		this.jobId = jobId;
		this.jobDesc = jobDesc;
		this.jobName = jobName;
	}

	// Property accessors

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobDesc() {
		return this.jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


}
