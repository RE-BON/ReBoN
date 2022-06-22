import '../../../styles/search-tags.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';

export default function Tags() {
  const [tag, setTags] = useState([]);
  useEffect(() => {
    axios
      .get('http://34.238.48.93:8080/api/tags')
      .then((response) => {
        setTags(response.data);
        console.log(tag);
      })
      .catch((error) => {
        console.log('error');
      });
  }, []);

  return (
    <ul className="tags-wrapper">
      <div>추천 태그로 검색해보세요.</div>
      {tag.map((item) => (
        <li>{item.name}</li>
      ))}
    </ul>
  );
}
