     package com.ydt.oa.entity;

	import java.io.Serializable;
	import javax.persistence.CascadeType;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.giro.common.entity.StringUUIDEntity;

	/**
	 * 付款流水记录
	 * 
	 * @author huchuqiao
	 * 
	 */
	@Entity
	@Table(name = "oa_paymentlog")
	public class PaymentLog extends StringUUIDEntity implements Serializable {

		public static final int PAYMENTLOG_STATUS_VALID = 1;
		
		private static final long serialVersionUID = 5048350808152431683L;
		private String payAmount; // 付款金额
		private String  payTime; // 付款时间
		private int paymentType; // 付款方式;现金   转账   支票
		private PaymentOrder paymentOrder; // 付款单
		private int status; // 付款状态
		private User user;   //经办人
		
		
		@OneToOne(
				cascade = { CascadeType.REFRESH },
				targetEntity = User.class, fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}

		public String getPayAmount() {
			return payAmount;
		}
		public void setPayAmount(String payAmount) {
			this.payAmount = payAmount;
		}


		
		public String getPayTime() {
		
			return payTime;
		}
		
		public void setPayTime(String payTime) {
		
			this.payTime = payTime;
		}
		
		public int getPaymentType() {
		
			return paymentType;
		}
		
		public void setPaymentType(int paymentType) {
		
			this.paymentType = paymentType;
		}
		
		@ManyToOne(
				cascade = { CascadeType.REFRESH },
				targetEntity = PaymentOrder.class, fetch = FetchType.LAZY)
		@JoinColumn
		public PaymentOrder getPaymentOrder() {
		
			return paymentOrder;
		}
		
		public void setPaymentOrder(PaymentOrder paymentOrder) {
		
			this.paymentOrder = paymentOrder;
		}
		
		public int getStatus() {
		
			return status;
		}
		
		public void setStatus(int status) {
		
			this.status = status;
		}


		
	}
