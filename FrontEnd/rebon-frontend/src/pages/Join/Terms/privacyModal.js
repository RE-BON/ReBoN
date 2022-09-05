import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faCheck, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import '../../../styles/terms-modal.css';

export default function PrivacyModal() {
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
          "RE-BON"은 본인인증서비스 제공을 위해 본인으로부터 아래의 개인정보를 입력받으며, 입력받은 개인정보의 이용 및 활용을 위해 다음의 사항에 대하여 본인으로부터 동의를
          받습니다.
          <br />
          <br />
          1. 입력받는 정보
          <br />
          가.정보항목
          <br />
          인증 : 성명, 이메일 아이디
          <br />
          나.입력방법 본인인증 요청시 서비스 페이지 또는 본인인증 팝업창 페이지에서 이용자가 직접 입력
          <br />
          <br />
          2. 개인정보의 이용목적과 수집 및 제공 정보 가.이용 목적 서비스(회원가입, ID/PW찾기 등)이용을 위한 본인인증 신용카드 명의자 확인
          <br />
          <br /> 3. 개인정보의 보유 및 이용기간 이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용목적이 달성되면 지체없이 파기합니다. 단, 다음의 정보에 대해서는 아래의 이유로
          명기한 기간 동안 보유합니다. <br />
          가.회사 내부 방침에 의한 정보보유 사유 본인확인 발생 및 신고기록,
          <br /> 보유 이유: 부정 이용 방지 및 민원 처리 <br />
          보유 기간: 5년 나.관계법령에 의한 정보보유 사유 이용자 불만 또는 분쟁 처리에 관한 기록 <br />
          보유 이유: 소비자보호에 관한 법률
          <br />
          보유 기간: 3년
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
