package com.smile.invest.member.service;

import com.smile.invest.member.dao.MemberDAO;
import com.smile.invest.member.dto.AuthorityDTO;
import com.smile.invest.member.dto.MemberDTO;
import com.smile.invest.member.dto.MemberRoleDTO;
import com.smile.invest.member.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {

        this.memberDAO = memberDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        MemberDTO member = memberDAO.findMemberById(memberId);

        if (member == null) {

            member = new MemberDTO();
        }

        // 권한 목록

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(member.getUserRoleList() != null){

            List<MemberRoleDTO> roleList = member.getUserRoleList();

            for(int i = 0; i < roleList.size(); i++){

                AuthorityDTO authority = roleList.get(i).getAuthority();
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }
        System.out.println("authorities = " + authorities);
        UserImpl user = new UserImpl(member.getUserId(), member.getUserPassword(), authorities);
        user.setDetails(member);

        return user;


    }

}
