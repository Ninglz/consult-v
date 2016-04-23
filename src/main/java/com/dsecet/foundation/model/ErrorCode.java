package com.dsecet.foundation.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author: Fablenas
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCode{

    /**
     * error code
     * info : 0x000 --> 0x2710 | 0 --> 10000
     * warning : 0x2711 --> 0x4E20 | 10001 --> 20000
     * error : 0x4E21 --> 0x9C40 | 20001 --> 40000
     * other 0x9C41 --> 0xFFFF | 40000 --> 65535
     */
    public static final int

            /* ============= web system status================ */

            SC_OK = 200,
            SC_MOVED_TEMPORARILY = 302,
            SC_NOT_FOUND = 404,
            SC_INTERNAL_SERVER_ERROR = 500,

            /* ===========info============== */
            SUCCESSFUL= 0x0000,

            /* ===========warning============== */
            BAD_CREDENTIAL = 10001,

            RESERVED_ENTITY = 10002,
            SMS_SEND_FAILED = 10003,
            USER_NO_SUCH = 10004,
            USER_DUPLICATE = 10005,

            CELL_NO_EMPTY = 10006,
            CELL_NO_INVALID = 10007,

            PASSWORD_EMPTY = 10008,
            PASSWORD_INVALID = 10009,

            VERIFICATION_CODE_EMPTY = 10010,
            VERIFICATION_CODE_INVALID = 10011,

            REGISTER_CELLNO_DUPLICATE = 10012,

            RESET_CELLNO_NOTEXIST = 10013,
            MODIFY_CELLNO_NOTEXIST = 10014,
            ORIGINAL_PASSWORD_BAD_CREDENTIAL = 10015,
            QUERY_STR_LENG_LIMIT = 10016,
            EQUIPMENT_MAC_DUPLICATE = 10017,
            EQUIPMENT_MAC_INVALID = 10018,
            EQUIPMENT_MAC_NO_SUCH = 10019,
            CONSUMER_ADD_EQUIPMENT_DUPLICATE = 10020,
            EQUIPMENT_REPEAT_ADDITION = 10021,
            CONSUMER_NO_USE_EQUIPMENT = 10022,

            ROLE_NO_SUCH = 10023,
            USER_EMPTY = 10024,
            PASSWORD_NOT_SAME = 10025,
            ROLE_NOT_DEL = 10026,
            ROLE_ADD_FAILED = 10027,
            USER_ADD_FAILED = 10028,

            EQUIPMENT_TYPE_EMPTY = 10029,
            EQUIPMENT_VERSION_BEFORE_UPDATED = 10030,
            EQUIPMENT_VERSION_VALID = 10031,

            NO_TREND_DATA_SHARE = 10032,
            NO_TRANSIENT_DATA_SHARE = 10033,
            
            EXPERTS_NAME_EMPTY = 10034,
            IDCARD_EMPTY = 10035,
            SENIORITY_EMPTY = 10036,
            INDUSTRY_EMPTY = 10037,
            GOODAT_EMPTY = 10038,
            DESCRIPTION_EMPTY = 10039,
            
            NAME_EMPTY = 10040,
            SEX_EMPTY = 10041,
            CITY_EMPTY = 10042,
            IMG_EMPTY = 10043,
            NOT_FOCUS_YOURSERLF = 10044,
            NOT_EXPERTS = 10045,
            EXPERTS_REGISTERED = 10046,
            DESCRIPTION_OR_CERTIFICATEIMG_EMPTY = 10047,
            EXPERTS_UPDATED = 10048,
            EXPERTS_NOT_AUDIT = 10049,
            COMMENT_SCORE_EMPTY = 10050,
            NO_COMMENT_YOURSELF = 10051,
            WITHDRAW_SINK_NOT_ENOUGH = 10052,
            ALIAPYUSERNAME_EMPTY = 10053,
    /* ===========error============== */
            CODE_REQUIRED = 40001,
            CODE_EXPIRE = 40002,
            CODE_INVALID = 40003,

            TICKET_REQUIRED = 40004,
            TICKET_INVALID = 40005,
            TICKET_EXPIRED = 40006,
            ILLEGAL_STATE = 40007,

            TOKEN_REQUIRED = 40008,
            TOKEN_INVALID = 40009,
            TOKEN_EXPIRED = 40010,

            VERSION_INVALID = 40011,

            ILLEGAl_PARAMETER = 40012,
            ENTITY_NO_SUCH = 40013,
            QUANTITY_INVALID = 40014,
            PASSWORD_DIFFERENCE = 40015,
            CUSTOMER_HAS_LOCKED = 40016,
            METHOD_ARGUMENT_NOT_VALID = 0xFFF0,
            USER_LOCK = 0xFFF1,
            UNDEFINDED = 0xFFFF;
    
    
    		

}
