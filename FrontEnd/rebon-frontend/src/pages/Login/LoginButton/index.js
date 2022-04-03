import React from 'react';
import '../../../styles/sns-btn.css';

export default function SNSButtons() {
  return (
    <div className="joinBtn-wrapper">
      <button className="join-btn naver-btn">
        <img className="logo-img" alt="naverLogo" src="image/naverLogo.png" />
        네이버 간편 가입하기
      </button>
      <button className="join-btn kakao-btn">
        <img className="logo-img" alt="kakaoLogo" src="image/kakaoLogo.png" />
        카카오 간편 가입하기
      </button>
      <button className="join-btn google-btn">
        <img className="logo-img" alt="googleLogo" src="image/googleLogo.png" />
        구글 간편 가입하기
      </button>
    </div>
  );
}
