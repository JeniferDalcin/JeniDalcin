(ns jeniferdalcin.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [jeniferdalcin.events :as events]
   [jeniferdalcin.routes :as routes]
   [jeniferdalcin.subs :as subs]))



;; Home Page

(defn home-title []
  (let [name (re-frame/subscribe [::subs/name])]
      [re-com/title
       :src   (at)
       :label (str @name)
       :level :level1])) ;; level 1 tem a ver com tamanho de fonte grau de importância

(defn self-apresentation []
  [:div.flex {:style {:padding-bottom "1em"}} 
   [re-com/p
    "A jeni é muito doidinha mas ama o Paulo muito muito."]])

(defn link-to-about-page []
  [re-com/md-circle-icon-button
   :md-icon-name "zmdi-face"
   :src      (at)
   :size :smaller
   :tooltip "about"
   :on-click #(re-frame/dispatch [::events/navigate :about])])

(defn link-to-daily-page []
  [re-com/md-circle-icon-button
   :md-icon-name    "zmdi-coffee"
   :src      (at)
   :size :smaller
   :tooltip "daily"
   :on-click #(re-frame/dispatch [::events/navigate :daily])])

(defn home-panel []
  [re-com/h-box
   :src      (at)
   :children 
   [[:div.flex
      [:img
       {:src "img/sunflower.png"
        :alt ""
        :style {:max-width "50vw"}}]]
    [:div.flex.center-block {:style {:align-self "center"}}
      [home-title]
      [self-apresentation]
      [link-to-about-page]
      [link-to-daily-page]]]])


(defmethod routes/panels :home-panel [] [home-panel])



;; About Page

(defn about-title []
  [re-com/title
   :src   (at)
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/md-circle-icon-button
   :md-icon-name    "zmdi-home"
   :src      (at)
   :size :smaller
   :tooltip "home"
   :on-click #(re-frame/dispatch [::events/navigate :home])])

(defn about-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[about-title]
              [link-to-daily-page]
              [link-to-home-page]]])

(defmethod routes/panels :about-panel [] [about-panel])




;; Daily Page

(defn daily-title []
  [re-com/title
   :src   (at)
   :label "Jornada de Aprendizado"
   :level :level1])


(defn daily-panel []
  [re-com/h-box
   :src      (at)
   :children 
   [[:div.flex {:style {:max-width "40vw"}} ;;solução temporária para div não interferir na outra div de baixo pode ser o valor 1 tb.
      [:img                               
       {:src "img/space.png"
        :alt ""
        :style {:max-width "100vw"}}]] ;;  a img que está setada com 100 da view dentro de uma div com 1 da view
    [:div.flex
      [daily-title]
      [link-to-about-page]
      [link-to-home-page]]]])

(defmethod routes/panels :daily-panel [] [daily-panel])




;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :src      (at)
     :height   "100%"
     :children [(routes/panels @active-panel)]]))
