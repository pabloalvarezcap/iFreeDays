package es.achosoftware.ifreedays.constants;

public class Querys {
	private Querys() {
	}

	private static final String VACATIONS_FOR_SKILL = "select v.* from vacation v"
			+ " inner join user_skill us on (us.user_id = v.user_id)" + " where us.skill_id = :skillId";

	private static final String ORDER_BY_V_DAY_ASC = " order by v.day asc";

	public static final String SELECT_USER_SKILLS = "select * from user u"
			+ " inner join user_skill us on (u.user_id = us.user_id)" + " where us.skill_id = :skillId";

	public static final String FIND_CALENDAR_BY_SKILL_ORDER_BY_DAY_ASC = VACATIONS_FOR_SKILL + ORDER_BY_V_DAY_ASC;

	public static final String FIND_CALENDAR_BY_SKILL_AND_DATE_ORDER_BY_DAY_ASC = VACATIONS_FOR_SKILL
			+ " and MONTH(v.day) = :month" + " and YEAR(v.day) = :year" + ORDER_BY_V_DAY_ASC;

}
