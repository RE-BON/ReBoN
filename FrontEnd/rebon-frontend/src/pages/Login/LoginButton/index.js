import React,{useState,useEffect} from 'react';
import '../../../styles/sns-btn.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Loading from '../Loading';

export default function SNSButtons() {
  const [loading, setLoading] = useState(true);

  const googleClientId=process.env.REACT_APP_GOOGLE_LOGIN
  const naverClientId=process.env.REACT_APP_NAVER_LOGIN;
  const kakaoClientId=process.env.REACT_APP_KAKAO_LOGIN;
  
  const params = new URLSearchParams(window.location.search);
  const navigate = useNavigate();

  useEffect(() => {
    const code = params.get("code");
    console.log(code);

    if(code != null){
      while(loading){
        console.log("로그인 후");
        axios
        .get(`http://3.34.139.61:8080/api/auth/naver/login/token?code=${code}`)
        .then((response) => {
          console.log(response);
          setLoading(false);
        })
        .catch((error) => {
          console.log('error');
          console.log(error);
          setLoading(false);
        });
      }
    }
  },[]);

  const googleLoginHandler = () =>{
    const GOOGLE_LOGIN_URL = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${googleClientId}&redirect_uri=http://localhost:3000/login&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email`;
    window.location.href = GOOGLE_LOGIN_URL;
  }

  const naverLoginHandler = () =>{
    const NAVER_LOGIN_URL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${naverClientId}&redirect_uri=http://localhost:3000loading`;
    window.location.href = NAVER_LOGIN_URL;
  }

  const kakaoLoginHandler = () =>{
    const KAKAO_LOGIN_URL = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoClientId}&redirect_uri=http://localhost:3000/login`;
    window.location.href = KAKAO_LOGIN_URL;
  }
  

  return (
    <div className="joinBtn-wrapper">
      <button className="join-btn naver-btn" onClick={naverLoginHandler}>
        <img className="logo-img" alt="naverLogo" src="image/naverLogo.png" />
        네이버 간편 로그인하기
      </button>
      <button className="join-btn kakao-btn" onClick={kakaoLoginHandler}>
        <img className="logo-img" alt="kakaoLogo" src="image/kakaoLogo.png" />
        카카오 간편 로그인하기
      </button>
      <button className="join-btn google-btn" onClick={googleLoginHandler}>
        <img className="logo-img" alt="googleLogo" src="image/googleLogo.png" />
        구글 간편 로그인하기
      </button>
    </div>
  );
}
