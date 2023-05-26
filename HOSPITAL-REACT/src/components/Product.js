import data from "../components/json/data.json";
import { useState } from "react";
import LockOpenIcon from "@mui/icons-material/LockOpen";
import bg123 from "../images/bg123.jpg";
import { Fade } from "react-reveal";

const Product = () => {
  const [filter, setFilter] = useState("main-menu");

  return (
    <section
      className="products section"
      id="products"
      style={{
        backgroundImage: `url(${bg123})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div className="products__container container">
        <Fade left delay={400}>
          <h2 className="section__title" style={{ textAlign: "left" }}>
            Born out of a vision to provide healthcare <br />
            to all strata of society
          </h2>
        </Fade>

        <ul className="products__filters">
          <Fade left delay={600}>
            <li
              className={
                "products__item products__line ${filter === 'main-menu' && 'active-product'} "
              }
              onClick={() => setFilter("main-menu")}
              data-filter=".delicacies"
              style={{ textAlign: "right" }}
            >
              <h3 className="products__title">Lab Tests</h3>
              <span className="products__stock">3 samples</span>
            </li>
            <li
              className={
                "products__item products__line ${filter === 'main-menu1' && 'active-product'} "
              }
              onClick={() => setFilter("main-menu1")}
              data-filter=".coffee"
              style={{ textAlign: "right" }}
            >
              <h3 className="products__title">Surgeries</h3>
              <span className="products__stock">3 samples</span>
            </li>
            <li
              className={
                "products__item products__line ${filter === 'main-menu2' && 'active-product'} "
              }
              onClick={() => setFilter("main-menu2")}
              data-filter=".cake"
              style={{ textAlign: "right", paddingLeft: "52px" }}
            >
              <h3 className="products__title">Health Packages</h3>
              <span className="products__stock">3 samples</span>
            </li>
          </Fade>
        </ul>

        <div className="products__content grid">
          {data.categories.map((category, index) => {
            return (
              category.category === filter && (
                <Fade key={index} bottom delay={100 * index}>
                  <article className="products__card main-menu">
                    <div className="products__shape">
                      <img src={category.image} className="products__img" />
                    </div>
                    <div className="products__data">
                      <h2 className="products__name">{category.title}</h2>

                      <button className="button products__button">
                        <LockOpenIcon />
                      </button>
                    </div>
                  </article>
                </Fade>
              )
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default Product;
