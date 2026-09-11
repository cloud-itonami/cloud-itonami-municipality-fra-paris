(ns culture.facts
  "Regional-culture catalog for Paris (Ville de Paris) -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"paris"
   [{:culture/id "paris.dish.french-onion-soup"
     :culture/name "French onion soup"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :dish
     :culture/summary "Onion soup gratinéed with cheese; the modern version dates from mid-19th-century Paris and is associated with Les Halles, the large food market in Paris."
     :culture/url "https://en.wikipedia.org/wiki/French_onion_soup"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.dish.jambon-beurre"
     :culture/name "Jambon-beurre"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :dish
     :culture/summary "French ham sandwich made of a baguette sliced open, spread with butter and filled with ham, also known as a parisien."
     :culture/url "https://en.wikipedia.org/wiki/Jambon-beurre"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.dish.paris-brest"
     :culture/name "Paris–Brest"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :dish
     :culture/summary "French dessert of choux pastry and praline-flavoured cream, created in 1910 by pâtissier Louis Durand to commemorate the Paris–Brest–Paris bicycle race."
     :culture/url "https://en.wikipedia.org/wiki/Paris%E2%80%93Brest"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.dish.st-honore-cake"
     :culture/name "St. Honoré cake"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :dish
     :culture/summary "French dessert invented in 1847 by Auguste Julien at the Chiboust bakery on rue Saint-Honoré in Paris."
     :culture/url "https://en.wikipedia.org/wiki/St._Honor%C3%A9_cake"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.product.brie-de-meaux"
     :culture/name "Brie de Meaux"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :product
     :culture/summary "Soft cheese from the Brie region near Paris (Île-de-France), a designated AOC product since 1980."
     :culture/url "https://en.wikipedia.org/wiki/Brie_de_Meaux"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.beverage.clos-montmartre"
     :culture/name "Clos Montmartre"
     :culture/name-local "Clos-Montmartre"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :beverage
     :culture/summary "Wine from the Clos-Montmartre vineyard on the Montmartre butte in Paris's 18th arrondissement, sold at auction for social causes."
     :culture/url "https://fr.wikipedia.org/wiki/Clos_Montmartre"
     :culture/url-provenance :wikipedia-fr
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.craft.haute-couture"
     :culture/name "Haute couture"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :craft
     :culture/summary "High-end custom fashion whose term is protected by French law; the industry became centred in Paris in the mid-19th century."
     :culture/url "https://en.wikipedia.org/wiki/Haute_couture"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.festival.fete-des-vendanges-de-montmartre"
     :culture/name "Fête des Vendanges de Montmartre"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :festival
     :culture/summary "Harvest festival held each second weekend of October since 1934 in Paris's 18th arrondissement, celebrating wine from the historic Clos Montmartre vineyard."
     :culture/url "https://fr.wikipedia.org/wiki/F%C3%AAte_des_vendanges_de_Montmartre"
     :culture/url-provenance :wikipedia-fr
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "paris.heritage.banks-of-the-seine"
     :culture/name "Banks of the Seine"
     :culture/municipality "paris"
     :culture/country "FRA"
     :culture/kind :heritage
     :culture/summary "The banks of the Seine in Paris (Rive Gauche and Rive Droite) were added by UNESCO to its list of World Heritage Sites in 1991."
     :culture/url "https://en.wikipedia.org/wiki/Banks_of_the_Seine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-fra-paris culture catalog "
                 "(ADR-2607171400): " (count (get catalog "paris"))
                 " Paris entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
