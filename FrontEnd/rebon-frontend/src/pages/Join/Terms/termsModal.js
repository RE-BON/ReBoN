import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import '../../../styles/terms-modal.css';

export default function TermsModal() {
  const navigate = useNavigate();
  const goBack = () => {
    navigate(-1);
  };
  return (
    <div className="terms-modal-wrapper">
      <div className="terms-modal-box">
        <div className="terms-modal-title">
          <FontAwesomeIcon icon={faAngleLeft} onClick={goBack} size="1x" />
          서비스 이용약관
          <FontAwesomeIcon icon={faXmark} size="1x" />
        </div>
        <div className="terms-modal-contents">
          제 1조(목적) <br /> <br />
          이 약관은 RE-BON이 제공하는 RE-BON 및 RE-BON관련 제반 서비스의 이용과 관련하여 회사와 회원과의 관리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.
          <br /> <br />
          제 2조(정의) <br /> <br />
          이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
          <br />
          ① "서비스"라 함은 구현되는 단말기(PC, 휴대형 단말기 각종 장치를 포함)와 상관없이 "회원"이 이용할 수 있는 "RE-BON" 및 "RE-BON"관련 제반 서비스를 의미합니다.
          <br />
          ② "회원"이라 함은 회사의 "서비스"에 접속하여 이 약관에 따라 "회사"가 제공하는 "서비스"를 이용하는 고객을 말합니다.
          <br />
          ③아이디(ID)라 함은 "회원"의 식별과 "서비스"이용을 위하여 "회원"이 정하고 "회사"가 승인하는 문자와 숫자의 조합을 의미합니다.
          <br />
          ④ "비밀번호"라 함은 "회원"이 부여받은 "아이디와 일치되는 회원"임을 확인하고 비밀보호를 위해 "회원"자신이 정한 문자 또는 숫자의 조합을 의미합니다.
          <br />
          ⑤"게시물"이라 함은 "회원"이 "서비스"를 이용함에 있어 "서비스상"에 게시한 부호,문자,음성,음향,화상 등의 정보형태의 글, 사진, 동영상 및 각종 파일과 링크 등을 의미합니다.
        </div>

        <div className="terms-select">
          <input type="checkbox" id="terms-select" name="marcketing" />
          <label for="terms-select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          위 사항을 모두 확인했고, 서비스 이용약관에 동의합니다
        </div>
        <div className="terms-modal-btn" onClick={goBack}>
          확인
        </div>
      </div>
    </div>
  );
}
