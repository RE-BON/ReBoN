import React,{useEffect} from 'react';
import '../../../styles/register.css';
import axios from 'axios';
import { useLocation } from 'react-router';

export default function Register() {
  const location = useLocation();
  useEffect(() => {
    console.log("location 정보");
    // if(location.state.email !=null){
    //   console.log(location.state.email);
    // }

},[]);

  const clickRegister = async() =>{

    await axios({
      method: 'post',
      url: "http://3.34.139.61:8080/api/members",
      data: {
        "email" : "hagus00@naver.com",
        "nickname" : "hagus99",
        "oauthProvider" : "naver",
        "agreed" : "true"	
      },
      dataType: 'application/json'
    })
    .then(function (response){
        console.log(response);
    })
    .catch(function (error){
        console.log(error);
    });

    // var params = new URLSearchParams();
    
    // params.append("email", "hagus00@naver.com");
    // params.append("nickname", "hagus99");
    // params.append("oauthProvider", "naver");
    // params.append("agreed", true);
    // const response = await axios.post("http://3.34.139.61:8080/api/members", params);
  }
  

  return (
    <div className="register-wrapper">
      <div className="centerBox">
        <div class="register-title">마지막 단계입니다!</div>
        <div class="register-body">
          <div className="register-left">
            <div>닉네임</div>
            <input className="nickname" type="text"></input>
          </div>
          <div className="register-right">
            <input type="checkbox" id="all-agree" /> <label for="all-agree">전체동의</label>
            <hr />
            <div className="checkbox">
              <input type="checkbox" id="service-agree" /> <label for="service-agree">서비스 이용약관 (필수)</label>
            </div>
            <div className="checkbox">
              <input type="checkbox" id="info-agree" /> <label for="info-agree">개인정보 취급방식 (필수)</label>
            </div>
            <div className="checkbox">
              <input type="checkbox" id="marketing-agree" /> <label for="marketing-agree">마케팅 수신 동의 (선택)</label>
            </div>
            <button className="start-btn" onClick={clickRegister}>ReBON 시작하기</button>
          </div>
        </div>
      </div>
    </div>
  );
}
