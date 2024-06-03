package com.example.minichat.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.databinding.ItemMessageBinding
import com.example.minichat.entities.LoginUsuarioData
import com.example.minichat.entities.MensajeWithData
import java.text.SimpleDateFormat

class MessageViewHolder(
  view: View
) : RecyclerView.ViewHolder(view) {
  val binding = ItemMessageBinding.bind(view)

  @SuppressLint("SimpleDateFormat")
  fun render(chatdata: MensajeWithData, loginUsuarioData: LoginUsuarioData?) {
    if (chatdata.mensaje.idUsuario == loginUsuarioData?.usuarioPerfil?.usuario?.id) {
      binding.textViewMessageItemNameUser.text = chatdata.usuario.perfil?.nombre ?: "Usuario"
      binding.textViewMessageItemText.text = chatdata.mensaje.mensaje
      binding.textViewMessageItemDateTime.text =
        chatdata.mensaje.fechaHora?.let { SimpleDateFormat("dd/MM/yyyy HH:mm").format(it) }

      binding.viewLeft.visibility = View.GONE
    } else {

      binding.textViewMessageItemNameUserLeft.text = chatdata.usuario.perfil?.nombre ?: "Usuario"
      binding.textViewMessageItemTextLeft.text = chatdata.mensaje.mensaje
      binding.textViewMessageItemDateTimeLeft.text =
        chatdata.mensaje.fechaHora?.let { SimpleDateFormat("dd/MM/yyyy HH:mm").format(it) }

      binding.viewRight.visibility = View.GONE
    }
  }
}