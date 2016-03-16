package cn.finance.model.base;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PersistentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log logger = LogFactory.getLog(PersistentObject.class);

	private Date createAt;

	private Date updateAt;
	
	private String deleted = "0";

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	private Integer id;

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int compareTo(Object obj) {
		PersistentObject o = (PersistentObject) obj;
		return new CompareToBuilder().append(this.id, o.id).toComparison();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj instanceof PersistentObject == false) {
			return false;
		}
		PersistentObject o = (PersistentObject) obj;
		return new EqualsBuilder().append(this.getId(), o.getId()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	public boolean isNull() {
		return (id == null);
	}
}
