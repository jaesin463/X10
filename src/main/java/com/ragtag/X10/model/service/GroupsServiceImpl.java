package com.ragtag.X10.model.service;

import com.ragtag.X10.model.dao.GroupsDao;
import com.ragtag.X10.model.dto.GroupMember;
import com.ragtag.X10.model.dto.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService{

    @Autowired
    GroupsDao groupsDao;

    @Override
    public int insertGroups(Groups groups) {
        int result = groupsDao.insertGroups(groups);

        // 만약 그룹 삽입이 성공했다면
        if (result > 0) {
            int groupId = groups.getGroupId();
            String groupLeaderId = groupsDao.selectGroupLeader(groupId);
            groupsDao.insertGroupMember(groupId, groupLeaderId);
        }

        return result;
    }

    @Override
    public List<Groups> selectOne(int groupId) {
        return groupsDao.selectOne(groupId);
    }

    @Override
    public List<Groups> selectUserGroups(String userId) {
        return groupsDao.selectUserGroups(userId);
    }

    @Override
    public List<GroupMember> selectAllUsers(int groupId) {
        return groupsDao.selectAllUsers(groupId);
    }

    @Override
    public int updateGroups(Groups groups) {
        return groupsDao.updateGroups(groups);
    }

    @Override
    public int deleteGroups(int groupId) {
        return groupsDao.deleteGroups(groupId);
    }

    @Override
    public int inviteUser(int groupId, String userId) {
        return groupsDao.inviteUser(groupId, userId);
    }

    @Override
    public int quitGroup(int groupId, String userId) {
        return groupsDao.quitGroup(groupId, userId);
    }
}
