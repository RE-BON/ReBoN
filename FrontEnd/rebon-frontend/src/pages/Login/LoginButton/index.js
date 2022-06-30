import React,{useHistory,useEffect} from 'react';
import '../../../styles/sns-btn.css';
import axios from 'axios';

export default function SNSButtons() {

  const googleClientId="66233621795-q2t3h8jvd5qc36n4j6j96ejrclnhu4eq.apps.googleusercontent.com";
  const naverClientId="LMoG83yi5hLpLfCCg1fa";
  const kakaoClientId="1c4b166500016e67df420423d02e187b"
  
  const params = new URLSearchParams(window.location.search);

  useEffect(() => {
    const code = params.get("code");
    console.log(code);

    if(code != null){
      console.log("로그인 후");
      axios
      .get(`http://3.34.139.61:8080/api/auth/google/login/token?code=${code}`)
      .then((response) => {
        console.log("로그인 후");
        console.log(response);
      })
      .catch((error) => {
        console.log('error');
        console.log(error);
      });
    }
  },[]);

  const googleLoginHandler = () =>{
    const GOOGLE_LOGIN_URL = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${googleClientId}&redirect_uri=http://localhost:3000/login&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email`;
    window.location.href = GOOGLE_LOGIN_URL;

  }

  const naverLoginHandler = () =>{
    const NAVER_LOGIN_URL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=LMoG83yi5hLpLfCCg1fa&state=STATE_STRING&redirect_uri=CALLBACK_URL`;
    window.location.href = NAVER_LOGIN_URL;
  }

  const kakaoLoginHandler = () =>{
    const KAKAO_LOGIN_URL = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoClientId}&redirect_uri=http://localhost:8080/admin`;
    window.location.href = KAKAO_LOGIN_URL;
  }
  

  return (
    <div className="joinBtn-wrapper">
      <button className="join-btn naver-btn" onClick={naverLoginHandler}>
        <img className="logo-img" alt="naverLogo" src="image/naverLogo.png" />
        네이버 간편 가입하기
      </button>
      <button className="join-btn kakao-btn">
        <img className="logo-img" alt="kakaoLogo" src="image/kakaoLogo.png" />
        카카오 간편 가입하기
      </button>
      <button className="join-btn google-btn" onClick={googleLoginHandler}>
        <img className="logo-img" alt="googleLogo" src="image/googleLogo.png" />
        구글 간편 가입하기
      </button>
    </div>
  );
}
