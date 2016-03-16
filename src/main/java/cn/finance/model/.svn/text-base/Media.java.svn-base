package cn.finance.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import cn.finance.model.base.PersistentObject;

/**
 *  媒体表
 */
public class Media extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * sv3 ID
	 */
	private Integer sv3Id;
	
	/**
	 * 媒体名
	 */
	private String name;    
	
	/**
	 * 媒体状态
	 */
	private Integer status;
	
	/**
	 * 媒体下的渠道列表
	 */
	private Set<Channel> channels = new HashSet<Channel>();

	public Integer getSv3Id() {
		return sv3Id;
	}

	public void setSv3Id(Integer sv3Id) {
		this.sv3Id = sv3Id;
	}
	
	public Set<Channel> getChannels() {
		return channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Media)) {
			return false;
		} else {
			Media media = (Media) obj;
			return new EqualsBuilder().append(this.getId(), media.getId())
					.append(this.getSv3Id(), media.getSv3Id()).isEquals();
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getSv3Id()).toHashCode();
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
}
