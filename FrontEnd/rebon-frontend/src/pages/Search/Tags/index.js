import '../../../styles/search-tags.css';

export default function Tags() {
  return (
    <ul className="tags-wrapper">
      <div>추천 태그로 검색해보세요.</div>
      {api.map((item) => (
        <li>{item}</li>
      ))}
    </ul>
  );
}
const api = ['영일대', '양덕', '구룡포', '칠포해수욕장', '영일대', '양덕', '구룡포', '칠포해수욕장', '구룡포', '칠포해수욕장'];
