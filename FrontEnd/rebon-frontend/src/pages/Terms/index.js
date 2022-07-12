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

  // const [selectAll, setSelectAll] = useState('white');
  // const onClickAll = () => {
  //   $(function ()
  //     // 1 . #checkAll 클릭
  //     $('#checkAll').click(function () {
  //       // 2. #checkAll이 체크되었을 때,
  //       // chk라는 name을 가진 input태그를 찾아 checked를 true로 정의
  //       if ($('#checkAll').prop('checked')) {
  //         $('input[name=agree]').prop('checked', true);
  //         // 3. #checkAll이 체크되지 않았을 때,
  //         // chk라는 name을 가진 input태그를 찾아 checked를 false로 정의
  //       } else {
  //         $('input[name=agree]').prop('checked', false);
  //       }
  //     });
  //   });
  // };

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
              <input type="checkbox" id="agreement-select" name="agree" />
              <label for="agreement-select">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              전체동의
            </div>

            <hr />
            <div className="agreement-select">
              <input type="checkbox" id="agreement-select1" name="agree" />
              <label for="agreement-select1">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              서비스 이용약관(필수)
              <Link to="/termsModal" style={{ marginLeft: '1em', color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>

            <div className="agreement-select">
              <input type="checkbox" id="agreement-select2" name="agree" />
              <label for="agreement-select2">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              개인정보 취급방식(필수)
              <Link to="/termsModal" style={{ color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>

            <div className="agreement-select">
              <input type="checkbox" id="agreement-select3" name="agree" />
              <label for="agreement-select3">
                <FontAwesomeIcon icon={faCheck} />
              </label>
              마케팅 수신동의(선택)
              <Link to="/termsModal" style={{ marginLeft: '1em', color: 'inherit', textDecoration: 'none' }}>
                <IoIosArrowForward />
              </Link>
            </div>
            <Link to="/">
              <button className="terms-btn">ReBON 시작하기</button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
