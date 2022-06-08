package com.example.oisan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="OIPAYUSAGE")
@SequenceGenerator(
        name = "OI_PAY_SEQ_GENERATOR",
        sequenceName = "OI_PAY_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class OiPayUsage {
	@Column(name="customer_id")
	private int customerId;
	
	@Id
	@Column(name="oi_pay_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OI_PAY_SEQ_GENERATOR")
	private int oiPayId;
	
	@Column
	private int type;
	
	@Column
	private int amount;
	
	@Column
	private int remain;
	
	@Column(name="auction_id")
	private Integer auctionId;
	
	@Column(name="create_at")
	private Date createAt;
	
	public OiPayUsage() {}
	
	public OiPayUsage(int customerId, int oiPayId, int type, int amount, int remain, int auctionId, Date createAt) {
		super();
		this.customerId = customerId;
		this.oiPayId = oiPayId;
		this.type = type;
		this.amount = amount;
		this.remain = remain;
		this.auctionId = auctionId;
		this.createAt = createAt;
	}
	
	public OiPayUsage(int customerId, int type, int amount, int remain, Integer auctionId, Date createAt) {
		super();
		this.customerId = customerId;
		this.type = type;
		this.amount = amount;
		this.remain = remain;
		this.auctionId = auctionId;
		this.createAt = createAt;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getOiPayId() {
		return oiPayId;
	}
	public void setOiPayId(int oiPayId) {
		this.oiPayId = oiPayId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
} 
