package com.dsecet.core.domain.user;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.dsecet.core.domain.record.City;
import com.dsecet.core.domain.use.Pushs;
import com.dsecet.foundation.model.Customer;
import com.dsecet.foundation.model.MediaItem;
import com.dsecet.util.EncryptUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: lxl
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "consumer")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"consumerDetails","pushs"})
//@EqualsAndHashCode(callSuper = true, exclude = {"equipments", "records"})
public class Consumer extends Customer{

    @Column(name = "weight")
    private double weight;

    @Column(name = "sex")
    private Long sex = 2L;

    @Column(name = "height")
    private double height;

    @Column(name = "age")
    private int age;

    @Column(name = "times")
    private int times;
    
    @Column(name = "address")
    private String address;
    
    /**
     * ucpass账号
     */
    @Column(name="client_number")
    private String clientNum;
    
    /**
     * ucpass密码
     */
    @Column(name="client_pwd")
    private String clientPwd;
    
    /**
     * APP类型
     */
    @Column(name="app_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private AppType AppType;
    
    /**
     * APP Token
     */
    @Column(name="app_token")
    private String AppToken;
    
    /**	
	 * 城市ID
	 */
	@ManyToOne
    @JoinColumn(name = "city_id", nullable = true, updatable = true)
	private City city;
	
    @Embedded
    @NotNull
    private Authentication authentication = new Authentication();
    
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "consumer_details_id")
    private ConsumerDetails consumerDetails;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "consumer_pushs", joinColumns = { @JoinColumn(name = "consumer_id") }, inverseJoinColumns = { @JoinColumn(name = "pushs_id") })
	private List<Pushs> pushs;
    
    /* 头像 */
    private String avatar;
    
    public enum AppType{
    	ANDROID,IOS,PC
    }
    
    public Consumer register(@NotBlank @Digits(integer = 11, fraction = 0) @Size(max = 11, min = 11) String username, @NotBlank String password) {
        super.signup(username, password);
        return this;
    }

    public boolean verifyPassword(@NotBlank String rawPassword){
        return getPassword().equals(EncryptUtils.password(rawPassword, getAccount().getPassword().getSalt()));
    }

    public String getEmail(){
        return getAuthentication().getEmail().getData();
    }

    public void setEmail(@NotBlank @Email String email){
        getAuthentication().getEmail().setData(email);
    }

    public String getRealName(){
        return getAuthentication().getRealName().getData()==null?"":getAuthentication().getRealName().getData();
    }

    public void setRealName(@NotBlank String realName){
        getAuthentication().getRealName().setData(realName);
    }
    
    public String getNickName(){
        return getAuthentication().getNickName().getData();
    }
    
    public void setNickName(@NotBlank String nickName){
    	getAuthentication().getNickName().setData(nickName);
    }

    public String getCellNo(){
        return getAuthentication().getCellNo().getData();
    }

    public void setCellNo(@NotBlank @Digits(integer = 11, fraction = 0) @Size(max = 11, min = 11) String cellNo){
        getAuthentication().getCellNo().setData(cellNo);
    }
    
    public String getIdCard(){
        return getAuthentication().getIdCard().getData();
    }

    public void setIdCard(@NotBlank String idCard){
        getAuthentication().getIdCard().setData(idCard);
    }
    
    public String getAvatar(){
    	if(StringUtils.isBlank(avatar)){
    		avatar="/information/000.png";
    	}
        return avatar;
    }
    
    
    /**
     * <p>Description:获取咨询者级别</p>
     * @author:宁良竹
     * @update: 2016年3月21日
     * @return
     */
    public Integer getConsumerLevel(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getConsuemrLevels()==null?0:getConsumerDetails().getConsuemrLevels().getLevels();
    	return 0;
    }
    
    /**
     * <p>Description:获取专家级别</p>
     * @author:宁良竹
     * @update: 2016年3月21日
     * @return
     */
    public Integer getExperLevel(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getExpertsLevels()==null?0:getConsumerDetails().getExpertsLevels().getLevels();
    	return 0;
    }
    
    /**
     * <p>Description:获取汇点</p>
     * @author:宁良竹
     * @update: 2016年3月21日
     * @return
     */
    public Double getSink(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getSink();
    	return 0D;
    }
    
    /**
     * <p>Description:获取积分</p>
     * @author:宁良竹
     * @update: 2016年3月21日
     * @return
     */
    public Double getIntegral(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getIntegral();
    	return 0D;
    }
    
    /**
     * 获取咨询者有效次数
     * */
    public Long getConsumerInvalidTime(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getConsumerInvalidTime();
    	return 0L;
    }
    
    /**
     * 获取咨询者无效次数
     * */
    public Long getConsumerValidTime(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getConsumerValidTime();
    	return 0L;
    }
    
    /**
     * 获取咨询师无效次数
     * */
    public Long getExpertsInvalidTime(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getExpertsInvalidTime();
    	return 0L;
    }
    
    /**
     * 获取咨询师有效次数
     * */
    public Long getExpertsValidTime(){
    	if(null!=getConsumerDetails())
    		return getConsumerDetails().getExpertsValidTime();
    	return 0L;
    }

}
