import React from 'react';
import '../../styles/join.css';

import SNSButtons from './JoinButton';

export default function Join() {
  return (
    <div className="join-wrapper">
      <div className="centerBox">
        <div class="join-title">
          빠르고 쉽게
          <br /> ReBON에 가입하세요!
        </div>
        <SNSButtons />
      </div>
    </div>
  );
}
