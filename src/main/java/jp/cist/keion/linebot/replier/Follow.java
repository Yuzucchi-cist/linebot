package jp.cist.keion.linebot.replier;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import jp.cist.keion.linebot.models.ClubMember;
import jp.cist.keion.linebot.repositoris.ClubMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

public class Follow implements Replier {
    private FollowEvent event;
    private ClubMemberRepository cmRepository;


    public Follow(FollowEvent event, ClubMemberRepository cmRepository){
        this.event = event;
        this.cmRepository = cmRepository;
    }
    @Override
    public Message reply() {
        String userId = this.event.getSource().getUserId();
        ClubMember member = new ClubMember(userId);
        this.cmRepository.save(member);
        String text = String.format("あなたのユーザーID:%s", userId);
        return new TextMessage(text);
    }
}
