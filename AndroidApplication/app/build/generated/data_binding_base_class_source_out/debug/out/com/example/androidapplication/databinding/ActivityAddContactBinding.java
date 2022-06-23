// Generated by view binder compiler. Do not edit!
package com.example.androidapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.androidapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddContactBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button bthAddContact;

  @NonNull
  public final EditText etNickname;

  @NonNull
  public final EditText etServer;

  private ActivityAddContactBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button bthAddContact, @NonNull EditText etNickname, @NonNull EditText etServer) {
    this.rootView = rootView;
    this.bthAddContact = bthAddContact;
    this.etNickname = etNickname;
    this.etServer = etServer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddContactBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddContactBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_contact, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddContactBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bthAddContact;
      Button bthAddContact = ViewBindings.findChildViewById(rootView, id);
      if (bthAddContact == null) {
        break missingId;
      }

      id = R.id.etNickname;
      EditText etNickname = ViewBindings.findChildViewById(rootView, id);
      if (etNickname == null) {
        break missingId;
      }

      id = R.id.etServer;
      EditText etServer = ViewBindings.findChildViewById(rootView, id);
      if (etServer == null) {
        break missingId;
      }

      return new ActivityAddContactBinding((ConstraintLayout) rootView, bthAddContact, etNickname,
          etServer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
