import React, { useState } from 'react';
import '../../../styles/sns-btn.css';

export default function SNSButtons() {
  const [loading, setLoading] = useState(true);

  const googleClientId = process.env.REACT_APP_GOOGLE_LOGIN;
  const naverClientId = process.env.REACT_APP_NAVER_LOGIN;
  const kakaoClientId = process.env.REACT_APP_KAKAO_LOGIN;

  const googleLoginHandler = () => {
    const GOOGLE_LOGIN_URL = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${googleClientId}&redirect_uri=http://localhost:3000/loading&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email`;
    window.location.href = GOOGLE_LOGIN_URL;
  };

  const naverLoginHandler = () => {
    const NAVER_LOGIN_URL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${naverClientId}&redirect_uri=http://localhost:3000/loading`;
    window.location.href = NAVER_LOGIN_URL;
  };

  const kakaoLoginHandler = () => {
    const KAKAO_LOGIN_URL = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoClientId}&redirect_uri=http://localhost:3000/loading`;
    window.location.href = KAKAO_LOGIN_URL;
  };

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
