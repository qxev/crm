package cn.finance.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import cn.finance.model.base.PersistentObject;

/**
 *  渠道表
 */
public class Channel extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * sv3 ID
	 */
	private Integer sv3Id;
	
	/**
	 * 媒体
	 */
	private Media media;
	
	/**
	 * 渠道名
	 */
	private String name;    
	
	/**
	 * 	 渠道状态  1有效 2无效
	 */
	private Integer status;
	
	/**
	 * 渠道下的账户
	 */
	private Set<Account> accounts = new HashSet<Account>();
	
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Integer getSv3Id() {
		return sv3Id;
	}

	public void setSv3Id(Integer sv3Id) {
		this.sv3Id = sv3Id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCutName() {
		if (name !=null && name.length()>6){
			return name.substring(0, 6);
		}
		return name;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
	public String getStatusDisplay() {
		if (status == 1){
			return "有效";
		} else if(status == 2) {
			return "无效";
		} else {
			return "";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Channel)) {
			return false;
		} else {
			Channel channel = (Channel) obj;
			return new EqualsBuilder().append(this.getId(), channel.getId())
					.append(this.getSv3Id(), channel.getSv3Id()).isEquals();
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getSv3Id()).toHashCode();
	}
}
