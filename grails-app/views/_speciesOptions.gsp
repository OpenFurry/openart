<option value="${it.id}">${it.speciesName}</option>
<g:render template="/speciesOptions" collection="${openfurry.Species.findAllWhere(parent: it)}" />
