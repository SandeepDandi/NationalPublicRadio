package com.example.npr;

import java.io.Serializable;


	@SuppressWarnings("serial")
	public class storyobject implements Serializable{
		String title,byline="no Value",mp3="No Audio",length="No Audio",pubdate,teaser,link="No Link",miniteaser;
		int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMiniteaser() {
			return miniteaser;
		}

		public void setMiniteaser(String miniteaser) {
			this.miniteaser = miniteaser;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getByline() {
			return byline;
		}

		public void setByline(String byline) {
			this.byline = byline;
		}

		public String getMp3() {
			return mp3;
		}

		public void setMp3(String mp3) {
			this.mp3 = mp3;
		}

		@Override
		public String toString() {
			return "storyobject [title=" + title + ", byline=" + byline
					+ ", mp3=" + mp3 + ", length=" + length + ", pubdate="
					+ pubdate + ", teaser=" + teaser + ", link=" + link
					+ ", miniteaser=" + miniteaser + ", id=" + id + "]";
		}

		public String getLength() {
			return length;
		}

		public void setLength(String length) {
			this.length = length;
		}

		public String getPubdate() {
			return pubdate;
		}

		public void setPubdate(String pubdate) {
			this.pubdate = pubdate;
		}

		public String getTeaser() {
			return teaser;
		}

		public void setTeaser(String teaser) {
			this.teaser = teaser;
		}
	}

