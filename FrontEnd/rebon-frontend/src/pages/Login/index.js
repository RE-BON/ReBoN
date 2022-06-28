import React from 'react';
import '../../styles/join.css';

import SNSButtons from './LoginButton';

export default function Login() {
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
