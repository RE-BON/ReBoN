import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import { IoIosArrowForward } from 'react-icons/io';
import { Link } from 'react-router-dom';
import '../../styles/terms.css';

export default function Terms() {
  const [name, setName] = useState('홍길동');
  const onChangeName = (e) => {
    setName(e.target.value);
  };

  return (
    <div className="terms-wrapper">
      <div className="termsBox">
        <div className="terms-title">마지막 단계입니다!</div>
        <div className="terms-container">
          <div className="nickname">
            닉네임
            <div className="nickname-bar">
              <input className="nickname-input" value={name} placeholder="" onChange={onChangeName}></input>
              <div className="nickname-btn">중복확인</div>
            </div>
            <div className="nickname-count">1/10</div>
          </div>

          <div>
            <div className="agreement-select">
              <input type="checkbox" id="agreement-select" name="marcketing" />
              <label for="agreement-select">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              전체동의
            </div>

            <hr />
            <div className="agreement-select">
              <input type="checkbox" id="agreement-select1" name="marcketing" />
              <label for="agreement-select1">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              서비스 이용약관(필수)
              <Link to="/termsModal" style={{ color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>

            <div className="agreement-select">
              <input type="checkbox" id="agreement-select2" name="marcketing" />
              <label for="agreement-select2">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              개인정보 취급방식(필수)
              <Link to="/termsModal" style={{ color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>

            <div className="agreement-select">
              <input type="checkbox" id="agreement-select3" name="marcketing" />
              <label for="agreement-select3">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              마케팅 수신동의(선택)
              <Link to="/termsModal" style={{ color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>
            <Link to="/search">
              <button className="terms-btn">ReBON 시작하기</button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
