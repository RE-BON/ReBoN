import React from 'react';
import '../../styles/join.css';

import SNSButtons from './JoinButton';

export default function Join() {
  return (
    <div className="join-wrapper">
      <div className="centerBox">
        <div class="join-title">
          빠르고 쉽게
          <br /> <span className="ReBON">ReBON</span>에 가입하세요!
          <br />
          <span className="subtitle">오늘도 다양한 리뷰들이 기다리고 있어요:)</span>
        </div>
        <SNSButtons />
        <hr className="divider" />
        <div>
          이미 회원이신가요? <br />
          로그인하고 위치별 맛집, 숙소를 찾아보세요.
        </div>
        <button className="join-bottom-btn">로그인</button>
      </div>
    </div>
  );
}
