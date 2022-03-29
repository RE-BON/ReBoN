import '../../../styles/myedit.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faXmark, faCamera, faCheck } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';

export default function Edit() {
  const [bgColor, setbgColor] = useState('white');
  const ChnageBgColor = (e) => {
    setbgColor(bgColor === 'white' ? '#ff6b6c' : 'white');
  };

  return (
    <div>
      <div className="edit-title">
        <div>회원정보 수정</div>
      </div>

      <div className="edit-info">
        <div className="edit-info-icon">
          <div className="icon-profile">
            <FontAwesomeIcon icon={faCircleUser} size="5x" color="#c4c4c4" />
            <span className="icon-camera">
              <FontAwesomeIcon icon={faCamera} size="1x" color="white" />
            </span>
          </div>
        </div>

        <div className="edit-info-title">닉네임</div>
        <div className="edit-info-name">
          <input type="text" placeholder="한글로 공백 없이 입력해주세요."></input>
          <div className="name-button">
            <FontAwesomeIcon icon={faXmark} size="1x" color="white" />
          </div>
        </div>

        <div className="edit-info-title ">이메일</div>
        <div className="edit-info-email">ReBon@gmail.com</div>

        <div className="edit-info-notice ">이메일 수신에 동의하시면 Rebon 주식회사가 제공하는 서비스의 업데이트 및 이벤트 소식을 받을 수 있습니다.</div>

        <div className="edit-info-agree " onClick={ChnageBgColor}>
          <span className="agree-button" style={{ backgroundColor: bgColor }}>
            <FontAwesomeIcon icon={faCheck} size="1x" />
          </span>
          <span>수신 동의하기</span>
        </div>

        <div className="edit-info-submit">
          <div className="cancel">취소</div>
          <div className="finished">수정완료</div>
        </div>
      </div>
    </div>
  );
}
