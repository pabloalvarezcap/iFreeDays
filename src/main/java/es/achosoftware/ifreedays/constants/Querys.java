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

	public static final String FIND_VACATIONS_BY_USER_ID = "select * from vacation v where v.user_id = :userId";
	
	public static final String FIND_PROJECTS_BY_CREATOR = "select p.* from projects p where p.creator_user_id = :userId";
	
	public static final String FIND_USERS_BY_PROJECT_ID = "select u.* from user u" + " inner join user_projects up on (up.user_id = u.user_id) " + " where up.project_id = :projectId";

	public static final String FIND_SKILLS_BY_PROJECT_ID = "select s.* from skill s inner join user_skill us on (us.skill_id = s.skill_id) inner join user_projects up on (up.user_id = us.user_id) where up.project_id = :projectId AND up.user_id = us.user_id";

	private static final String VACATIONS_FOR_SKILL_AND_FOR_PROJECT = "select v.* from vacation v"
			+ " inner join user_skill us on (us.user_id = v.user_id)" + " inner join user_projects up on (up.user_id = us.user_id)" + " where us.skill_id = :skillId AND up.project_id = :projectId";
	private static final String FIND_CALENDAR_BY_SKILL_AND_FOR_PROJECT_AND_DATE_ORDER_BY_DAY_ASC = VACATIONS_FOR_SKILL_AND_FOR_PROJECT + " and MONTH(v.day) = :month" + " and YEAR(v.day) = :year" + ORDER_BY_V_DAY_ASC;
	
	public static final String FIND_CALENDAR_BY_SKILL_BY_PROJECT_ORDER_BY_DAY_ASC = VACATIONS_FOR_SKILL_AND_FOR_PROJECT + ORDER_BY_V_DAY_ASC;
	
	public static final String FIND_VACATIONS_BY_USER_ID_AND_BY_PROJECT_ID = "select v.* from vacation v" + "inner join user_projects up on (up.user_id = v.user_id)" + "where v.user_id = :userId AND up.project_id = :projectId";
}
