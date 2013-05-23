/**
 * 
 */
package jabara.fsa.entity;

import jabara.fsa.beanvalidation.PhoneNumber;
import jabara.fsa.beanvalidation.RequireMaxCharCount;
import jabara.jpa.entity.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author jabaraster
 */
@Entity
public class EOrganization extends EntityBase<EOrganization> {
    private static final long serialVersionUID            = -1919689080831039839L;

    private static final int  MAX_CHAR_COUNT_NAME         = 1000;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_NAME * 3)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_NAME)
    protected String          name;

    private static final int  MAX_CHAR_COUNT_ADDRESS      = 100;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_ADDRESS * 3)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_ADDRESS)
    protected String          address;

    private static final int  MIN_CHAR_COUNT_PHONE_NUMBER = 10;
    private static final int  MAX_CHAR_COUNT_PHONE_NUMBER = 11 + 2;               // 11:数字のみにしたときの最大桁 2:ハイフンの数
    /**
     * 
     */
    @Column(nullable = true, length = MAX_CHAR_COUNT_PHONE_NUMBER)
    @Size(min = MIN_CHAR_COUNT_PHONE_NUMBER, max = MAX_CHAR_COUNT_PHONE_NUMBER)
    @PhoneNumber(message = "電話番号の書式ではありません。")
    protected String          phoneNumber;

    /**
     * @return addressを返す.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @return nameを返す.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return phoneNumberを返す.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @param pAddress addressを設定.
     */
    public void setAddress(final String pAddress) {
        this.address = pAddress;
    }

    /**
     * @param pName nameを設定.
     */
    public void setName(final String pName) {
        this.name = pName;
    }

    /**
     * @param pPhoneNumber phoneNumberを設定.
     */
    public void setPhoneNumber(final String pPhoneNumber) {
        this.phoneNumber = pPhoneNumber;
    }

}
