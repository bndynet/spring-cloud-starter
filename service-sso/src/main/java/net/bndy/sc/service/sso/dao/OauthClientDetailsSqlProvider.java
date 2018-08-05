package net.bndy.sc.service.sso.dao;

import java.util.List;
import java.util.Map;
import net.bndy.sc.service.sso.model.OauthClientDetails;
import net.bndy.sc.service.sso.model.OauthClientDetailsCriteria.Criteria;
import net.bndy.sc.service.sso.model.OauthClientDetailsCriteria.Criterion;
import net.bndy.sc.service.sso.model.OauthClientDetailsCriteria;
import org.apache.ibatis.jdbc.SQL;

public class OauthClientDetailsSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String countByExample(OauthClientDetailsCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("oauth_client_details");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String deleteByExample(OauthClientDetailsCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("oauth_client_details");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String insertSelective(OauthClientDetails record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("oauth_client_details");
        
        if (record.getClient_id() != null) {
            sql.VALUES("client_id", "#{client_id,jdbcType=VARCHAR}");
        }
        
        if (record.getClient_name() != null) {
            sql.VALUES("client_name", "#{client_name,jdbcType=VARCHAR}");
        }
        
        if (record.getClient_secret() != null) {
            sql.VALUES("client_secret", "#{client_secret,jdbcType=VARCHAR}");
        }
        
        if (record.getResource_ids() != null) {
            sql.VALUES("resource_ids", "#{resource_ids,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.VALUES("scope", "#{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorized_grant_types() != null) {
            sql.VALUES("authorized_grant_types", "#{authorized_grant_types,jdbcType=VARCHAR}");
        }
        
        if (record.getWeb_server_redirect_uri() != null) {
            sql.VALUES("web_server_redirect_uri", "#{web_server_redirect_uri,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorities() != null) {
            sql.VALUES("authorities", "#{authorities,jdbcType=VARCHAR}");
        }
        
        if (record.getAccess_token_validity() != null) {
            sql.VALUES("access_token_validity", "#{access_token_validity,jdbcType=INTEGER}");
        }
        
        if (record.getRefresh_token_validity() != null) {
            sql.VALUES("refresh_token_validity", "#{refresh_token_validity,jdbcType=INTEGER}");
        }
        
        if (record.getAdditional_information() != null) {
            sql.VALUES("additional_information", "#{additional_information,jdbcType=VARCHAR}");
        }
        
        if (record.getAutoapprove() != null) {
            sql.VALUES("autoapprove", "#{autoapprove,jdbcType=TINYINT}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String selectByExample(OauthClientDetailsCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("client_id");
        } else {
            sql.SELECT("client_id");
        }
        sql.SELECT("client_name");
        sql.SELECT("client_secret");
        sql.SELECT("resource_ids");
        sql.SELECT("scope");
        sql.SELECT("authorized_grant_types");
        sql.SELECT("web_server_redirect_uri");
        sql.SELECT("authorities");
        sql.SELECT("access_token_validity");
        sql.SELECT("refresh_token_validity");
        sql.SELECT("additional_information");
        sql.SELECT("autoapprove");
        sql.FROM("oauth_client_details");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        OauthClientDetails record = (OauthClientDetails) parameter.get("record");
        OauthClientDetailsCriteria example = (OauthClientDetailsCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("oauth_client_details");
        
        if (record.getClient_id() != null) {
            sql.SET("client_id = #{record.client_id,jdbcType=VARCHAR}");
        }
        
        if (record.getClient_name() != null) {
            sql.SET("client_name = #{record.client_name,jdbcType=VARCHAR}");
        }
        
        if (record.getClient_secret() != null) {
            sql.SET("client_secret = #{record.client_secret,jdbcType=VARCHAR}");
        }
        
        if (record.getResource_ids() != null) {
            sql.SET("resource_ids = #{record.resource_ids,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.SET("scope = #{record.scope,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorized_grant_types() != null) {
            sql.SET("authorized_grant_types = #{record.authorized_grant_types,jdbcType=VARCHAR}");
        }
        
        if (record.getWeb_server_redirect_uri() != null) {
            sql.SET("web_server_redirect_uri = #{record.web_server_redirect_uri,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorities() != null) {
            sql.SET("authorities = #{record.authorities,jdbcType=VARCHAR}");
        }
        
        if (record.getAccess_token_validity() != null) {
            sql.SET("access_token_validity = #{record.access_token_validity,jdbcType=INTEGER}");
        }
        
        if (record.getRefresh_token_validity() != null) {
            sql.SET("refresh_token_validity = #{record.refresh_token_validity,jdbcType=INTEGER}");
        }
        
        if (record.getAdditional_information() != null) {
            sql.SET("additional_information = #{record.additional_information,jdbcType=VARCHAR}");
        }
        
        if (record.getAutoapprove() != null) {
            sql.SET("autoapprove = #{record.autoapprove,jdbcType=TINYINT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("oauth_client_details");
        
        sql.SET("client_id = #{record.client_id,jdbcType=VARCHAR}");
        sql.SET("client_name = #{record.client_name,jdbcType=VARCHAR}");
        sql.SET("client_secret = #{record.client_secret,jdbcType=VARCHAR}");
        sql.SET("resource_ids = #{record.resource_ids,jdbcType=VARCHAR}");
        sql.SET("scope = #{record.scope,jdbcType=VARCHAR}");
        sql.SET("authorized_grant_types = #{record.authorized_grant_types,jdbcType=VARCHAR}");
        sql.SET("web_server_redirect_uri = #{record.web_server_redirect_uri,jdbcType=VARCHAR}");
        sql.SET("authorities = #{record.authorities,jdbcType=VARCHAR}");
        sql.SET("access_token_validity = #{record.access_token_validity,jdbcType=INTEGER}");
        sql.SET("refresh_token_validity = #{record.refresh_token_validity,jdbcType=INTEGER}");
        sql.SET("additional_information = #{record.additional_information,jdbcType=VARCHAR}");
        sql.SET("autoapprove = #{record.autoapprove,jdbcType=TINYINT}");
        
        OauthClientDetailsCriteria example = (OauthClientDetailsCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    public String updateByPrimaryKeySelective(OauthClientDetails record) {
        SQL sql = new SQL();
        sql.UPDATE("oauth_client_details");
        
        if (record.getClient_name() != null) {
            sql.SET("client_name = #{client_name,jdbcType=VARCHAR}");
        }
        
        if (record.getClient_secret() != null) {
            sql.SET("client_secret = #{client_secret,jdbcType=VARCHAR}");
        }
        
        if (record.getResource_ids() != null) {
            sql.SET("resource_ids = #{resource_ids,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.SET("scope = #{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorized_grant_types() != null) {
            sql.SET("authorized_grant_types = #{authorized_grant_types,jdbcType=VARCHAR}");
        }
        
        if (record.getWeb_server_redirect_uri() != null) {
            sql.SET("web_server_redirect_uri = #{web_server_redirect_uri,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorities() != null) {
            sql.SET("authorities = #{authorities,jdbcType=VARCHAR}");
        }
        
        if (record.getAccess_token_validity() != null) {
            sql.SET("access_token_validity = #{access_token_validity,jdbcType=INTEGER}");
        }
        
        if (record.getRefresh_token_validity() != null) {
            sql.SET("refresh_token_validity = #{refresh_token_validity,jdbcType=INTEGER}");
        }
        
        if (record.getAdditional_information() != null) {
            sql.SET("additional_information = #{additional_information,jdbcType=VARCHAR}");
        }
        
        if (record.getAutoapprove() != null) {
            sql.SET("autoapprove = #{autoapprove,jdbcType=TINYINT}");
        }
        
        sql.WHERE("client_id = #{client_id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth_client_details
     *
     * @mbg.generated Tue Aug 07 18:09:57 CST 2018
     */
    protected void applyWhere(SQL sql, OauthClientDetailsCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}