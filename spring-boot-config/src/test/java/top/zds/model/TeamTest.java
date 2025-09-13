package top.zds.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zds.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class TeamTest {
    @Resource
    private Team team;
    @Test
    void testTeam() {
//        log.info("team: {}",team);
//       测试姓名
        assertEquals( "zds", team.getLeader());
////        测试手机格式
//        assertEquals( "1736238426", team.getPhone());
////        测试团队年龄
//        assertEquals( "1", team.getAge());
    }
    @Test
    void testTeam2() {
        assertEquals( "xxxxx", team.getLeader());
    }

}