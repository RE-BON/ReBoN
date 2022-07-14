import '../../styles/footer.css';

export default function Footer() {
  return (
    <div className="footer-wrapper">
      <img src="/image/logo.png" alt="logo" />
      <div className="footer-contents">
        <div className="footer-title">
          <u>이용약관</u>
          <span>이메일주소무단수집거부</span>
        </div>

        <span className="footer-contents-email">이메일: Rebon@naver.com</span>
        <span className="footer-contents-num">전화번호: 010-1234-5678</span>
        <span className="footer-contents-work">업무시간: 평일10:00~18:00(토, 일, 공휴일 휴무)</span>
        <div className="footer-reserve">Copyright Rebon All Right reserved</div>
      </div>
    </div>
  );
}
