import { Link } from 'react-router-dom';
import '../../../styles/search-tags.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';

export default function Tags() {
  const [tag, setTags] = useState([]);
  useEffect(() => {
    axios
      .get('http://3.34.139.61:8080/api/tags')
      .then((response) => {
        setTags(response.data);
      })
      .catch((error) => {
        console.log('Tags error');
      });
  }, []);

  return (
    <ul className="tags-wrapper">
      <div>추천 태그로 검색해보세요.</div>
      {tag.slice(0, 10).map((item) => (
        <>
          <Link to={`/main?name=${item.name}`} state={{ item }}>
            <li key={item.name.toString()}>{item.name}</li>
          </Link>
        </>
      ))}
    </ul>
  );
}
