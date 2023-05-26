import blog1 from "../images/blog1.jpg";
import blog2 from "../images/blog2.jpg";
import CallMadeIcon from "@mui/icons-material/CallMade";
import { Fade } from "react-reveal";

const Blog = () => {
  return (
    <section className="blog section" id="blog">
      <div className="blog__container container">
        <Fade left delay={400}>
          <h2 className="section__title" style={{ textAlign: "left" }}>
            Our Recent Blogs with <br />
            insightful topic
          </h2>
        </Fade>

        <div className="blog__content grid">
          <Fade bottom delay={800}>

          <article className="blog__card">
            <div className="blog__image">
              <img src={blog1} alt="" className="blog__img" />
              <a href="#" className="blog__button">
                <CallMadeIcon />
              </a>
              <div className="blog__stats">
                <div className="blog__reaction">
                  <i className="bx bx-comment"></i>
                  <span>12</span>
                </div>
                <div className="blog__reaction">
                  <i className="bx bx-show"></i>
                  <span>76,5k</span>
                </div>
              </div>
            </div>

            <div className="blog__data">
              <h2 className="blog__title">
                How to protect from Parkinson Infographic
              </h2>
              <p className="blog__description">
                The blogs about coffee will help you a lot about how to grow
                coffee beans with hign standard.
              </p>
            </div>
          </article>
          <article className="blog__card">
            <div className="blog__image">
              <img src={blog2} alt="" className="blog__img" />
              <a href="#" className="blog__button">
                <CallMadeIcon />
              </a>
              <div className="blog__stats">
                <div className="blog__reaction">
                  <i className="bx bx-comment"></i>
                  <span>96</span>
                </div>
                <div className="blog__reaction">
                  <i className="bx bx-show"></i>
                  <span>356,7k</span>
                </div>
              </div>
            </div>

            <div className="blog__data">
              <h2 className="blog__title">
                How to protect yourself from COVID-19
              </h2>
              <p className="blog__description">
                The blogs about coffee will help you a lot about how to make a
                coffee with a good place coffee.
              </p>
            </div>
          </article>
          </Fade>
        </div>
      </div>
    </section>
  );
};

export default Blog;
