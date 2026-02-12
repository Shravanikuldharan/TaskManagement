package com.TaskManagement.Security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.TaskManagement.Enum.Permission;
import com.TaskManagement.Enum.Role;

public class PermissionConfig {
	public static Map<Role, Set<Permission>>getRoleBasedPermission() 
	{
		Map<Role, Set<Permission>>map = new HashMap<>();
		
		map.put(Role.ADMIN, new HashSet<>(Arrays.asList(Permission.ISSUE_CREATED,
														Permission.ISSUE_VIEW,
														Permission.ISSUE_EDIT,
														Permission.ISSUE_DELETE,
														Permission.ISSUE_ASSIGN,
														Permission.COMMENT_ADD,
														Permission.COMMENT_DELETE,
														Permission.USER_MANAGE
																			)));
		
		map.put(Role.MANAGER, new HashSet<>(Arrays.asList(Permission.ISSUE_CREATED,
														  Permission.ISSUE_VIEW,
														  Permission.ISSUE_EDIT,
														  Permission.COMMENT_ADD,
														  Permission.ISSUE_ASSIGN,
														  Permission.WORKFLOW_TRANSACTION
																			)));
		
		map.put(Role.TESTER, new HashSet<>(Arrays.asList(Permission.ISSUE_VIEW,
														 Permission.ISSUE_CREATED,
														 Permission.COMMENT_ADD
																			)));
		
		return map;
	}
}
