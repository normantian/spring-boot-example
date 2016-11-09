package org.sun.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name="seckill")
public class Seckill {
	@XmlElement
	private long seckillId;

	@XmlElement
	private String name;

	@XmlElement
	private int number;

	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@XmlElement
	private Date startTime;

	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@XmlElement
	private Date endTime;

	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@XmlElement
	private Date createTime;

	/**
	 * @return the seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}

	/**
	 * @param seckillId the seckillId to set
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Seckill [seckillId=" + seckillId + ", name=" + name
				+ ", number=" + number + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}

}
