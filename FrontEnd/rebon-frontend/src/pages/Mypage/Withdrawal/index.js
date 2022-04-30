import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import '../../../styles/withdrawal.css';

export default function Withdrawal() {
  const [bgColor, setbgColor] = useState('white');
  const ChnageBgColor = (e) => {
    setbgColor(bgColor === 'white' ? '#ff6b6c' : 'white');
  };

  return (
    <>
      <div className="title">회원탈퇴</div>
      <div className="withdrawal-Wrapper">
        <div className="withdrawal-notice">회원탈퇴 유의사항</div>
        <div className="withdrawal-notice-describe">
          지금까지 ReBoN을 이용해주셔서 대단히 감사합니다. 회원 탈퇴를 위해 아래 사항을 확인해주시기 바랍니다.
          <br />
          <br />
          1. 회원님의 모든 정보가 삭제됩니다.
          <br />
          <br />
          2. 탈퇴 후 7일간 재 가입이 불가능 합니다.
          <div className="withdrawal-agree" onClick={ChnageBgColor}>
            <span className="agree-button" style={{ backgroundColor: bgColor }}>
              <FontAwesomeIcon icon={faCheck} size="1x" />
            </span>
            <span>위 사항을 모두 확인했고, 회원탈퇴에 동의합니다.</span>
          </div>
          <Link to="/logout" style={{ color: 'inherit', textDecoration: 'none' }}>
            <div className="withdrawal-submit">회원 탈퇴</div>
          </Link>
        </div>
      </div>
    </>
  );
}
