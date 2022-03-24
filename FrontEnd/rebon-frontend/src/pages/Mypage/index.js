import '../../styles/mypage.css';
import SideMenu from './SideMenu';
import '../../styles/myedit.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faXmark, faCamera } from '@fortawesome/free-solid-svg-icons';
import Header from '../../components/Header';
export default function Mypage() {
  return (
    <div className="my-wrapper">
      <Header />
      <div className="myedit-wrapper">
        <div className="myedit-nav">
          <SideMenu />
        </div>
        <div className="myedit-content-wrapper">
          <div className="myedit-content-title">
            <div>회원정보 수정</div>
          </div>

          <div className="myedit-content-info">
            <div className="myedit-content-info-icon">
              <div className="icon-profile-icon">
                <FontAwesomeIcon icon={faCircleUser} size="5x" color="#c4c4c4" />
                <span className="icon-profile-icon-camera">
                  <FontAwesomeIcon icon={faCamera} size="1x" color="white" />
                </span>
              </div>
            </div>

            <div className="myedit-content-info-title">이름</div>
            <div className="myedit-content-info-name">
              <input type="text" placeholder="한글로 공백 없이 입력해주세요."></input>
              <div className="name-button">
                <FontAwesomeIcon icon={faXmark} size="1x" color="white" />
              </div>
            </div>

            <div className="myedit-content-info-title ">이메일</div>
            <div className="myedit-content-info-email">
              <input type="text" placeholder="ReBon@gmail.com"></input>
              <div className="email-button">중복확인</div>
            </div>

            <div className="myedit-content-info-title ">휴대폰 번호</div>
            <div className="myedit-content-info-phone">
              <input type="text" placeholder="010-1234-5678"></input>
              <div className="phone-button">인증번호 요청</div>
            </div>

            <div className="myedit-content-info-button">
              <div className="cancel">취소</div>
              <div className="finished">수정완료</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
