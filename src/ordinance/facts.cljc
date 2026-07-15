(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Paris -- the SIXTH
  municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin for the
  first five) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL Ville de Paris (paris.fr / cdn.paris.fr /
  dematpr.apps.paris.fr) URL -- never fabricated. An ordinance not in
  this table has NO spec-basis, full stop; extend `catalog`, do not
  invent an id/url/number.

  Both entries below were verified by directly reading the source PDF
  text via the Read tool: the tourist-rental commercial-premises
  regulation's own header states the exact Conseil de Paris session
  dates, deliberation number, and publication date; the Terrasses et
  Étalages regulation's own text states the original 2011-06-11 arrêté
  municipal and lists every amendment date up to the current
  2023-12-13 consolidated version.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"paris"
   [{:ordinance/id "paris.reglement-locaux-commerciaux-meubles-tourisme"
     :ordinance/title "Règlement municipal fixant les conditions de délivrance des autorisations visant la location de locaux à usage commercial en meublés de tourisme"
     :ordinance/municipality "paris"
     :ordinance/country "FRA"
     :ordinance/kind :ordinance
     :ordinance/number "délibération 2025 DLH 106"
     :ordinance/url "https://cdn.paris.fr/paris/2025/04/18/reglement-municipal-locaux-commerciaux_avril-2025-dIoN.pdf"
     :ordinance/url-provenance :official-ville-de-paris
     :ordinance/enacted-date "2025-04-11"
     :ordinance/last-revised-date "2025-04-18"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:short-term-rental :zoning}}
    {:ordinance/id "paris.reglement-terrasses-etalages"
     :ordinance/title "Règlement des Terrasses et Étalages"
     :ordinance/municipality "paris"
     :ordinance/country "FRA"
     :ordinance/kind :ordinance
     :ordinance/number "arrêté municipal du 11 juin 2011"
     :ordinance/url "https://dematpr.apps.paris.fr/dematpr/doc/Reglement_Etalages_Terrasses.pdf"
     :ordinance/url-provenance :official-ville-de-paris
     :ordinance/enacted-date "2011-06-11"
     :ordinance/last-revised-date "2023-12-13"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:public-space :licensing}}]})

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
      :note (str "cloud-itonami-municipality-fra-paris Wave 0 (ADR-2607141700): "
                 (count (get catalog "paris")) " Paris entries seeded with "
                 "an official Ville de Paris (paris.fr) citation. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
