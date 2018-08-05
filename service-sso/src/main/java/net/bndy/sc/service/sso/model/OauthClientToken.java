package net.bndy.sc.service.sso.model;

import java.io.Serializable;
import java.util.Arrays;

public class OauthClientToken implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_token.token_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    private String token_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_token.authentication_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    private String authentication_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_token.user_name
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    private String user_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_token.client_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    private String client_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_token.token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    private byte[] token;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth_client_token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_token.token_id
     *
     * @return the value of oauth_client_token.token_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String getToken_id() {
        return token_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_token.token_id
     *
     * @param token_id the value for oauth_client_token.token_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public void setToken_id(String token_id) {
        this.token_id = token_id == null ? null : token_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_token.authentication_id
     *
     * @return the value of oauth_client_token.authentication_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String getAuthentication_id() {
        return authentication_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_token.authentication_id
     *
     * @param authentication_id the value for oauth_client_token.authentication_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id == null ? null : authentication_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_token.user_name
     *
     * @return the value of oauth_client_token.user_name
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_token.user_name
     *
     * @param user_name the value for oauth_client_token.user_name
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_token.client_id
     *
     * @return the value of oauth_client_token.client_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_token.client_id
     *
     * @param client_id the value for oauth_client_token.client_id
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public void setClient_id(String client_id) {
        this.client_id = client_id == null ? null : client_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_token.token
     *
     * @return the value of oauth_client_token.token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public byte[] getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_token.token
     *
     * @param token the value for oauth_client_token.token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public void setToken(byte[] token) {
        this.token = token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OauthClientToken other = (OauthClientToken) that;
        return (this.getToken_id() == null ? other.getToken_id() == null : this.getToken_id().equals(other.getToken_id()))
            && (this.getAuthentication_id() == null ? other.getAuthentication_id() == null : this.getAuthentication_id().equals(other.getAuthentication_id()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getClient_id() == null ? other.getClient_id() == null : this.getClient_id().equals(other.getClient_id()))
            && (Arrays.equals(this.getToken(), other.getToken()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getToken_id() == null) ? 0 : getToken_id().hashCode());
        result = prime * result + ((getAuthentication_id() == null) ? 0 : getAuthentication_id().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getClient_id() == null) ? 0 : getClient_id().hashCode());
        result = prime * result + (Arrays.hashCode(getToken()));
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_token
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", token_id=").append(token_id);
        sb.append(", authentication_id=").append(authentication_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", client_id=").append(client_id);
        sb.append(", token=").append(token);
        sb.append("]");
        return sb.toString();
    }
}