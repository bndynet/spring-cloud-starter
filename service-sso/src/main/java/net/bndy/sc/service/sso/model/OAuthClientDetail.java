package net.bndy.sc.service.sso.model;

public class OAuthClientDetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.client_id
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String client_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.client_name
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String client_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.client_secret
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String client_secret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.resource_ids
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String resource_ids;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.scope
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String scope;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.authorized_grant_types
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String authorized_grant_types;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.web_server_redirect_uri
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String web_server_redirect_uri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.authorities
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String authorities;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.access_token_validity
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private Integer access_token_validity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.refresh_token_validity
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private Integer refresh_token_validity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.additional_information
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private String additional_information;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.autoapprove
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    private Byte autoapprove;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.client_id
     *
     * @return the value of oauth_client_details.client_id
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.client_id
     *
     * @param client_id the value for oauth_client_details.client_id
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setClient_id(String client_id) {
        this.client_id = client_id == null ? null : client_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.client_name
     *
     * @return the value of oauth_client_details.client_name
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getClient_name() {
        return client_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.client_name
     *
     * @param client_name the value for oauth_client_details.client_name
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setClient_name(String client_name) {
        this.client_name = client_name == null ? null : client_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.client_secret
     *
     * @return the value of oauth_client_details.client_secret
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getClient_secret() {
        return client_secret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.client_secret
     *
     * @param client_secret the value for oauth_client_details.client_secret
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret == null ? null : client_secret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.resource_ids
     *
     * @return the value of oauth_client_details.resource_ids
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getResource_ids() {
        return resource_ids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.resource_ids
     *
     * @param resource_ids the value for oauth_client_details.resource_ids
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setResource_ids(String resource_ids) {
        this.resource_ids = resource_ids == null ? null : resource_ids.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.scope
     *
     * @return the value of oauth_client_details.scope
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getScope() {
        return scope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.scope
     *
     * @param scope the value for oauth_client_details.scope
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.authorized_grant_types
     *
     * @return the value of oauth_client_details.authorized_grant_types
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getAuthorized_grant_types() {
        return authorized_grant_types;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.authorized_grant_types
     *
     * @param authorized_grant_types the value for oauth_client_details.authorized_grant_types
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setAuthorized_grant_types(String authorized_grant_types) {
        this.authorized_grant_types = authorized_grant_types == null ? null : authorized_grant_types.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.web_server_redirect_uri
     *
     * @return the value of oauth_client_details.web_server_redirect_uri
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getWeb_server_redirect_uri() {
        return web_server_redirect_uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.web_server_redirect_uri
     *
     * @param web_server_redirect_uri the value for oauth_client_details.web_server_redirect_uri
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setWeb_server_redirect_uri(String web_server_redirect_uri) {
        this.web_server_redirect_uri = web_server_redirect_uri == null ? null : web_server_redirect_uri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.authorities
     *
     * @return the value of oauth_client_details.authorities
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.authorities
     *
     * @param authorities the value for oauth_client_details.authorities
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.access_token_validity
     *
     * @return the value of oauth_client_details.access_token_validity
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public Integer getAccess_token_validity() {
        return access_token_validity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.access_token_validity
     *
     * @param access_token_validity the value for oauth_client_details.access_token_validity
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setAccess_token_validity(Integer access_token_validity) {
        this.access_token_validity = access_token_validity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.refresh_token_validity
     *
     * @return the value of oauth_client_details.refresh_token_validity
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public Integer getRefresh_token_validity() {
        return refresh_token_validity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.refresh_token_validity
     *
     * @param refresh_token_validity the value for oauth_client_details.refresh_token_validity
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setRefresh_token_validity(Integer refresh_token_validity) {
        this.refresh_token_validity = refresh_token_validity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.additional_information
     *
     * @return the value of oauth_client_details.additional_information
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public String getAdditional_information() {
        return additional_information;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.additional_information
     *
     * @param additional_information the value for oauth_client_details.additional_information
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information == null ? null : additional_information.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.autoapprove
     *
     * @return the value of oauth_client_details.autoapprove
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public Byte getAutoapprove() {
        return autoapprove;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.autoapprove
     *
     * @param autoapprove the value for oauth_client_details.autoapprove
     *
     * @mbg.generated Tue Aug 07 09:26:54 CST 2018
     */
    public void setAutoapprove(Byte autoapprove) {
        this.autoapprove = autoapprove;
    }
}