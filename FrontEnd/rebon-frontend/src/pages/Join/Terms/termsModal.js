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
        <div class="terms-modal-contents">
          회전목마(1절) <br /> <br />
          Umm 내가 슬플 때마다 이 노래가 찾아와 세상이 둥근 것처럼 우린 동글동글 인생은 회전목마 우린 매일 달려가 언제쯤 끝나 난 잘 몰라 (huh, huh, huh)
          <br /> <br />
          어머 (어머), 벌써 (벌써) 정신없이 달려왔어 (왔어) Speed up (speed up) 어제로 돌아가는 시곌 보다가 어려워 (어려워) 어른이 되어가는 과정이 uh huh On the road, 24 시간이
          아까워 uh huh Big noise, everything brand new 어렸을 때처럼 바뀌지 않는 걸 찾아 나섰단 말야 왜냐면 그때가 더 좋았어 난 So let me go back 타임머신 타고 I'll go back 승호가
          좋았을 때처럼만 내가 슬플 때마다 이 노래가 찾아와 세상이 둥근 것처럼 우리 인생은 회전목마 우린 매일 달려가 언제쯤 끝나 난 잘 몰라 빙빙 돌아가는 회전목마처럼 영원히 계속될
          것처럼 빙빙 돌아온 우리의 시간처럼 인생은 회전목마 ayy 어머 (어머) 벌써 (벌써) 정신없이 달려왔어 (왔어) Speed up (speed up) 어제로 돌아가는 시곌 보다가 청춘까지 뺏은 현재
          탓할 곳은 어디 없네 Twenty two 세에게 너무 큰 벽 그게 말로 하고 싶어도 어려웠어 가끔은 어렸을 때로 돌아가 불가능하단 건 나도 잘 알아 그 순간만 고칠 수 있다면 지금의 나는
          더 나았을까 달려가는 미터기 돈은 올라가 기사님과 어색하게 눈이 맞아 창문을 열어보지만 기분은 좋아지지 않아 그래서 손을 밖으로 쭉 뻗어 쭉 뻗어 흔들리는 택시는 어느새
          목적지에 도달했다고 해 방 하나 있는 내 집 안의 손에 있던 짐들은 내가 힘들 때마다 이 노래가 찾아와 세상이 둥근 것처럼 우리 인생은 회전목마 우린 계속 달려가 언제쯤 끝날지
          잘 몰라 빙빙 돌아가는 회전목마처럼 영원히 계속될 것처럼 빙빙 돌아온 우리의 시간처럼 인생은 회전목마 I'm on a TV show You would never even know 사실 얼마나 많이 불안했는지
        </div>

        <div class="terms-select">
          <input type="checkbox" id="terms-select" name="marcketing" />
          <label for="terms-select">
            <FontAwesomeIcon icon={faCheck} />
          </label>
          위 사항을 모두 확인했고, 서비스 이용약관에 동의합니다
        </div>
        <div className="terms-modal-btn">확인</div>
      </div>
    </div>
  );
}
